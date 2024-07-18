package com.feeder.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feeder.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
