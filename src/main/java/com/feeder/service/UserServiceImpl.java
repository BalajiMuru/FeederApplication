package com.feeder.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feeder.exception.ReasourceNotFoundException;
import com.feeder.entity.User;
import com.feeder.payload.UserDto;
import com.feeder.respository.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper  modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User save = this.userRepo.save(user);
		return this.userToDto(save);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user =  this.userRepo.findById(userId).orElseThrow(()->new ReasourceNotFoundException("User not found","Id",userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		User use = this.userRepo.save(user);
		UserDto userDt =this.userToDto(use);
		return userDt;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ReasourceNotFoundException("User not found","Id",userId));
		UserDto useDt = this.userToDto(user);
		return useDt;
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDto1 = users.stream().map(u -> this.userToDto(u)).collect(Collectors.toList());
		return userDto1;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new  ReasourceNotFoundException("User not found","Id",userId));
		this.userRepo.delete(user);
		
	}
	
	
	private User dtoToUser(UserDto userDto) {
//		User user =  new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		User user = this.modelMapper.map(userDto, User.class);
		return user;
			
	}

	private UserDto userToDto(User user) {
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
		
	}
}
