package com.smartreception.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.smartreception.entity.Role;
import com.smartreception.exception.NotFoundException;
import com.smartreception.service.RoleService;
import com.smartreception.util.TestUtils;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleControllerTest {
  
  @InjectMocks
  private RoleController roleController;
  
  @Mock
  private RoleService roleService;
  
  private MockMvc mockMvc;
  
  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();
  }
  
  @Test
  public void testGetRoleShouldReturnNotDeletedRoles() throws Exception {
    roleController.getRoles();
    verify(roleService).getRoles();
  }
  
  /**
   * Test GET /roles as a request handler
   * @throws Exception
   */
  @Test
  public void testGetAllURIShouldReturnStatusOK() throws Exception {
    this.mockMvc.perform(get("/roles")).andExpect(status().isOk());  
  }
  
  /**
   * Test DELETE /roles/{id}
   * @throws Exception
   */
  @Test
  public void testDeleteURIShouldReturnStatusNoContent() throws Exception {
    // data
    Role fakeRole = TestUtils.createRole(1L, "fake-role", true);
    
    // mock
    when(roleService.getRoleById(1L)).thenReturn(fakeRole);
    
    this.mockMvc.perform(delete("/roles/{id}", fakeRole.getId()))
        .andExpect(status().isNoContent());
  }
  
  /**
   * Test delete existing role
   * @throws Exception
   */
  @Test
  public void testDeleteShouldDeleteRoleIfExists() throws Exception{
    // data
    long id = 2L;
    Role fakeRole = TestUtils.createRole(id, "fake-role", true);
    
    // mock
    when(roleService.getRoleById(id)).thenReturn(fakeRole);
    
    roleController.delete(id);
    verify(roleService).delete(id);
  }
  
  /**
   * Test delete non-existing role
   * @throws Exception
   */
  @Test(expected = NotFoundException.class)
  public void testDeleteShouldThrowExceptionIfNotExists() throws Exception {
    // data
    long id = 2L;
    
    roleController.delete(id);
  }

}
