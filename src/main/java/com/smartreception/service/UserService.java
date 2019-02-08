package com.smartreception.service;

import com.smartreception.entity.User;

public interface UserService {
  public long insert(User user);
  public void delete(long id);
  public int update(User user, long id);
  public User getUserById(long id);
}