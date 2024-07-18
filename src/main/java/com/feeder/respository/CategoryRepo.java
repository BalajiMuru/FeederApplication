package com.feeder.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feeder.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
