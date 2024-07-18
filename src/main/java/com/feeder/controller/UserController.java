package com.feeder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feeder.payload.ApiResponse;
import com.feeder.payload.UserDto;
import com.feeder.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	//post create user
	@PostMapping()
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
	{
		UserDto userDto1 = this.userService.createUser(userDto);
		return new ResponseEntity<>(userDto1,HttpStatus.CREATED) ;	
	}
	
	//put update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userId)
	{
		UserDto userDto1 = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(userDto1);	
	}
	
	
	//delete delete use
	@DeleteMapping("/{userId}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable Integer userId){
		this.userService.deleteUser(userId);
		return  new ResponseEntity(new ApiResponse("User Deleted suceessfully",true), HttpStatus.OK);
	}
	
	
	
	//Get all user 
	@GetMapping()
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return new  ResponseEntity<>(this.userService.getAllUser(),HttpStatus.OK);
		
	}
	
	//Get  user by id
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
		
	}
	
 
}
