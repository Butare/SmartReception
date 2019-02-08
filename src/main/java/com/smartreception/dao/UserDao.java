package com.smartreception.dao;

import com.smartreception.entity.User;

public interface UserDao {
  public long insert(User user);
  public User getUserById(long id);
  public int update(User user);
}