package com.feeder.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feeder.entity.Category;
import com.feeder.entity.User;
import com.feeder.exception.ReasourceNotFoundException;
import com.feeder.payload.CategoryDto;
import com.feeder.payload.UserDto;
import com.feeder.respository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category  = this.modelMapper.map(categoryDto, Category.class);
		Category addcategory = this.categoryRepo.save(category);
		return this.modelMapper.map(addcategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category  cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ReasourceNotFoundException("Category","categoryId",categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryTitle());
		Category catSave =  this.categoryRepo.save(cat);
	    CategoryDto CategoryDto1 = this.modelMapper.map(cat, CategoryDto.class);
	    return CategoryDto1;
				
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat  = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ReasourceNotFoundException("Category","Category_id",categoryId));
		this.categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ReasourceNotFoundException("Category","category",categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

//	List<User> users = this.userRepo.findAll();
//	List<UserDto> userDto1 = users.stream().map(u -> this.userToDto(u)).collect(Collectors.toList());
//	return userDto1;
	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> catdto = categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catdto;
	}

}
