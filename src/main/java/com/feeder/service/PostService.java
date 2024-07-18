package com.feeder.service;

import java.util.List;

import com.feeder.entity.Post;
import com.feeder.payload.PostDto;
import com.feeder.payload.PostResponse;

public interface PostService {
	
	//Create 
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto , Integer postId);
	
    //delete
	void deletePost(Integer postId);
	
	//get all user
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBY,String sortDir);
	
	//get single post
	PostDto getPostById(Integer PostId);
	
	
	//get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all posts by User
	List<PostDto> getPostByUser(Integer userId);
	
	
	//search post
	List<Post> searchPosts(String keyword);
	

}
