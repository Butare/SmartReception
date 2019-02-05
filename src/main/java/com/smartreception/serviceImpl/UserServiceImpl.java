package com.smartreception.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smartreception.dao.UserDao;
import com.smartreception.entity.User;
import com.smartreception.service.UserService;

@Component
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;
  
  @Override
  public long insert(User user) {
    return userDao.insert(user);
  }

  @Override
  public void delete(long id) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public User update(long id) {
    // TODO Auto-generated method stub
    return null;
  }

}
