package com.smartreception.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.smartreception.controller.EmployeeController;
import com.smartreception.entity.Employee;
import com.smartreception.service.EmployeeService;
import com.smartreception.util.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {
  
  private EmployeeController employeeController;
  
  @Mock
  private EmployeeService employeeService;
  
  private MockMvc mockMvc;
  
  @Before
  public void setup() {
    employeeController = new EmployeeController(employeeService);
    this.mockMvc = MockMvcBuilders
        .standaloneSetup(employeeController)
        .build();
  }
  
  @Test
  public void testInsertShouldReturnInsertedEmployee() throws Exception {
    
    // data
    Employee newEmployee = TestUtils.createEmployee();
    // test
    Employee employee = employeeController.insert(newEmployee);
    // assert & verify
    assertEquals(employee, newEmployee);
    verify(employeeService, times(1)).insert(newEmployee);
  }
  
  @Test
  public void testPostURIShouldReturnCreatedStatus() throws Exception {
    
    // data
    Employee newEmployee = TestUtils.createEmployee();
    
    // test
    this.mockMvc.perform(
      post("/employees")
      .contentType(MediaType.APPLICATION_JSON)     
      .content(TestUtils.convertObjectToStringBytes(newEmployee))
      )
      .andExpect(status().isCreated());
    
    // verify
    verify(employeeService).insert(newEmployee);
  }
  
  @Test
  public void testUpdateShouldReturnUpdatedEmployee() throws Exception {
    long id = 1;
    Employee toUpdateEmployee = TestUtils.createEmployee(id, "u-001", "test-company");
    
    // mock
    when(employeeService.getEmployeeById(id)).thenReturn(toUpdateEmployee);
    Employee updatedEmployee = employeeController.update(toUpdateEmployee, id);

    // assert & verify
    assertEquals(toUpdateEmployee, updatedEmployee);
    verify(employeeService).update(toUpdateEmployee, id);
  }
  
  @Test
  public void testPutURIShouldReturnOKstatus() throws Exception {
    // data
    long id = 1;
    Employee employee = TestUtils.createEmployee(id);
    
    // test
    this.mockMvc.perform(put("/employees/{id}", employee.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtils.convertObjectToStringBytes(employee)))
        .andExpect(status().isOk());
    
    // verify
    verify(employeeService, times(1)).getEmployeeById(id);
    verify(employeeService, times(1)).update(employee, id);
  }
  
  /**
   * Test GET /users as a method
   * 
   * @throws Exception
   */
  @Test
  public void testGetAllShouldReturnAllEmployeesList() throws Exception {
	employeeController.getAll();
	verify(employeeService).getAll();
  }
  
  /**
   * Test GET /users as a request handler
   * @throws Exception
   */
  @Test
  public void testGetAllURIShouldReturnStatusOK() throws Exception {
	this.mockMvc.perform(get("/employees")).andExpect(status().isOk());  
  }
  
  @Test
  public void testDeleteURIShouldReturnStatusNoContentWhenPass() throws Exception {
	// data
	Employee user = TestUtils.createEmployee(1L);
	this.mockMvc.perform(delete("/employees/{id}", user.getId()))
			.andExpect(status().isNoContent());
  }
  
}
