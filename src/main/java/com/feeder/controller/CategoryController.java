package com.feeder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feeder.entity.Category;
import com.feeder.payload.ApiResponse;
import com.feeder.payload.CategoryDto;
import com.feeder.payload.UserDto;
import com.feeder.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	//post create Category
	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto cateDto = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(cateDto,HttpStatus.CREATED);
		
	}
	

	//put update Category
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto , @PathVariable("categoryId") Integer catId){
		CategoryDto updateCategoryDto = this.categoryService.updateCategory(categoryDto, catId);
		return ResponseEntity.ok(updateCategoryDto);
		
	}
	
	//delete delete Category
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> deleteCategory(@PathVariable("categoryId") Integer catId ){
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity(new ApiResponse("User Deleted suceessfully",true),HttpStatus.OK);	
	}
	//Get all Category
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto>  list =  this.categoryService.getCategories();
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	//Get  Category by id
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Integer catId ){
		
		return ResponseEntity.ok(this.categoryService.getCategory(catId));
		
		
	}

}
