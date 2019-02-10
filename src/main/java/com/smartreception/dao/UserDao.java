package com.smartreception.dao;

import java.util.List;

import com.smartreception.entity.User;

public interface UserDao {
  public long insert(User user);
  public User getUserById(long id);
  public int update(User user);
  public List<User> getAll();
}