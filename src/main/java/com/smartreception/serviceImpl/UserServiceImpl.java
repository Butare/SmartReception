package com.smartreception.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.smartreception.dao.UserDao;
import com.smartreception.entity.User;
import com.smartreception.exception.NotFoundException;
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
  public int update(User user, long id) {
    if (ObjectUtils.isEmpty(userDao.getUserById(id))) {
      throw new NotFoundException("User does not exist. id = "+ id);
    }
    user.setId(id);
    return userDao.update(user);
  }

  @Override
  public User getUserById(long id) {
    User user = userDao.getUserById(id);
    if (ObjectUtils.isEmpty(user)) {
      throw new NotFoundException("User does not exist. id = "+ id);
    }
    return user;
  }

  @Override
  public List<User> getAll() {
	return userDao.getAll();
  }

}
