package com.smartreception.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
  public void testPostURIShouldReturnCreatedStatus() throws Exception {
    
    // data
    User newUser = TestUtils.createUser();
    
    // test
    this.mockMvc.perform(
      post("/users")
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
    User toUpdateUser = TestUtils.createUser(id, "u-001", "test-company");
    
    // mock
    when(userService.getUserById(id)).thenReturn(toUpdateUser);
    User updatedUser = userController.update(toUpdateUser, id);

    // assert & verify
    assertEquals(toUpdateUser, updatedUser);
    verify(userService).update(toUpdateUser, id);
  }
  
  @Test
  public void testPutURIShouldReturnOKstatus() throws Exception {
    // data
    long id = 1;
    User user = TestUtils.createUser(id);
    
    // test
    this.mockMvc.perform(put("/users/{id}", user.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtils.convertObjectToStringBytes(user)))
        .andExpect(status().isOk());
    
    // verify
    verify(userService, times(1)).getUserById(id);
    verify(userService, times(1)).update(user, id);
  }
  
  /**
   * Test GET /users as a method
   * 
   * @throws Exception
   */
  @Test
  public void testGetAllShouldReturnAllUsersList() throws Exception {
	userController.getAll();
	verify(userService).getAll();
  }
  
  /**
   * Test GET /users as a request handler
   * @throws Exception
   */
  @Test
  public void testGetAllURIShouldReturnStatusOK() throws Exception {
	this.mockMvc.perform(get("/users")).andExpect(status().isOk());  
  }
  
  @Test
  public void testDeleteURIShouldReturnStatusNoContentWhenPass() throws Exception {
	// data
	User user = TestUtils.createUser(1L);
	this.mockMvc.perform(delete("/users/{id}", user.getId()))
			.andExpect(status().isNoContent());
  }
  
}
