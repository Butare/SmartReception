package com.smartreception.serviceImpl;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.smartreception.dao.UserDao;
import com.smartreception.entity.User;
import com.smartreception.exception.NotFoundException;
import com.smartreception.util.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
  
  @InjectMocks
  private UserServiceImpl userServiceImpl;
  
  @Mock
  private UserDao userDao;
  
  @Test
  public void testDeleteUserShouldPassWhenNotNull() throws Exception {
	// data
	long id = 1L;
	User userToDelete = TestUtils.createUser(id);
	
	when(userDao.getUserById(id)).thenReturn(userToDelete);
	userServiceImpl.delete(id);
	
	verify(userDao).delete(id);
  }
  
  @Test(expected = NotFoundException.class)
  public void testDeleteUserShouldThrowWhenNull() throws Exception {
	  Long id = 1L;
	  when(userDao.getUserById(id)).thenReturn(null);
	  userServiceImpl.delete(id);
  }
}
