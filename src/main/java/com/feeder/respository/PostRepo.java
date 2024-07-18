package com.feeder.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feeder.entity.Category;
import com.feeder.entity.Post;
import com.feeder.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	
}
