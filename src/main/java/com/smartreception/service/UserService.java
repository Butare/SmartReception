package com.smartreception.service;

import com.smartreception.entity.User;

public interface UserService {
  public User insert(User user);
  public void delete(long id);
  public User update(long id);
}