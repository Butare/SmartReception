package com.smartreception.dao;

import org.springframework.stereotype.Component;

import com.smartreception.entity.User;

@Component
public class UserDao {
  
  public User insert(User user) {
    return user;
  }
}
