package com.feeder.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.feeder.entity.Category;
import com.feeder.entity.Post;
import com.feeder.entity.User;
import com.feeder.exception.ReasourceNotFoundException;
import com.feeder.payload.PostDto;
import com.feeder.payload.PostResponse;
import com.feeder.respository.CategoryRepo;
import com.feeder.respository.PostRepo;
import com.feeder.respository.UserRepo;

@Service
public class PostServiceImpl implements PostService{
	
	 @Autowired
	 PostRepo postRepo;
	 
	 @Autowired
	 UserRepo userRepo;
	 
	 @Autowired
	 CategoryRepo categoryRepo;

	 @Autowired
	 ModelMapper modelMapper;
	 
	 
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ReasourceNotFoundException("User","UserId",userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ReasourceNotFoundException("Category","CategoryId",categoryId));		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ReasourceNotFoundException("Post", "PostId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
	    Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ReasourceNotFoundException("Post", "PostId", postId));
		
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
//		Sort.by(sortBy).descending()
		
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort = Sort.by(sortBy).ascending();
		}else
		{
			sort = Sort.by(sortBy).descending();
		}
		
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allPosts = pagePost.getContent();
//		List<Post> allPosts = this.postRepo.findAll();
		List<PostDto>  postDtos = allPosts.stream().map((post)-> this.modelMapper.map(post,PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getNumberOfElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer PostId) {
		Post postsingle = this.postRepo.findById(PostId).orElseThrow(() ->new ReasourceNotFoundException("Post","PostId",PostId));


		return this.modelMapper.map(postsingle, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()-> new ReasourceNotFoundException("Category","CategoryId",categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat); 
		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ReasourceNotFoundException("User","User",userId));
		List<Post> posts =  this.postRepo.findByUser(user);
		List<PostDto> postDto = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
