package com.smartreception.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.smartreception.controller.UserController;
import com.smartreception.service.UserService;
import com.smartreception.util.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
  
  @InjectMocks
  UserController userController;
  
  @Mock
  private UserService userService;
  
  private MockMvc mockMvc;
  
  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders
        .standaloneSetup(userController)
        .build();
  }
  
  @Test
  public void testInsert() throws Exception {
    
    // data
    User newUser = TestUtils.createUser();
    // mock
    when(userService.insert(newUser)).thenReturn(newUser);
    // test
    User user = userController.insert(newUser);
    
    // Test Http POST
    this.mockMvc.perform(
        MockMvcRequestBuilders.post("/user")
        .contentType(MediaType.APPLICATION_JSON)     
        .content(TestUtils.convertObjectToJsonBytes(newUser)))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    
    // assert & verify
    assertEquals(user, newUser);
    verify(userService, times(2)).insert(newUser);
  }
}
