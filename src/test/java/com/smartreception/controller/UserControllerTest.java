package com.smartreception.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.smartreception.controller.UserController;
import com.smartreception.dao.UserDao;
import com.smartreception.entity.User;
import com.smartreception.service.UserService;
import com.smartreception.util.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
  
  @InjectMocks
  UserController userController;
  
  @Mock
  private UserService userService;
  
  @Mock 
  private UserDao userDao;
  
  private MockMvc mockMvc;
  
  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders
        .standaloneSetup(userController)
        .build();
  }
  
  @Test
  public void testInsertShouldReturnInsertedUser() throws Exception {
    
    // data
    User newUser = TestUtils.createUser();
    // test
    User user = userController.insert(newUser);
    // assert & verify
    assertEquals(user, newUser);
    verify(userService, times(1)).insert(newUser);
  }
  
  @Test
  public void testInsertPostShouldReturnCreatedStatus() throws Exception {
    
    // data
    User newUser = TestUtils.createUser();

    this.mockMvc.perform(
      MockMvcRequestBuilders.post("/user")
      .contentType(MediaType.APPLICATION_JSON)     
      .content(TestUtils.convertObjectToStringBytes(newUser))
      )
      .andExpect(status().isCreated());
    
    // verify
    verify(userService).insert(newUser);
  }
  
  @Test
  public void testUpdateShouldReturnUpdatedUser() throws Exception {
    long id = 1;
    User dbUser = TestUtils.createUser(id);
    User toUpdateUser = TestUtils.createUser(1L, "u-001", "test-company");
    
    // mock
    when(userDao.getUserById(id)).thenReturn(dbUser);
    
    User updatedUser = userController.update(toUpdateUser, id);
    
    assertEquals(toUpdateUser, updatedUser);
    
  }
}
