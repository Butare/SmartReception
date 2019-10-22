package com.smartreception.util;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartreception.entity.Employee;
import com.smartreception.entity.Role;

public class TestUtils {
  
  public static final MediaType APPLICATION_JSON_UTF8 = 
      new MediaType(MediaType.APPLICATION_JSON.getSubtype(), 
      MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
  
  public static String convertObjectToStringBytes(Object object) throws IOException {
    return new ObjectMapper().writeValueAsString(object);
    
  }
  
  public static Employee createEmployee() {
    return createEmployee(0L);
  }
  
  public static Employee createEmployee(long id) {
    return createEmployee(id, "user-001", "reception-test");
  }
  
  /**
   * To create {@link Employee}
   * @param userId
   * @param organizationName
   * @return new {@link Employee}
   */
  public static Employee createEmployee(long id, String userId, String organizationName) {
    Employee employee = new Employee();
    employee.setId(id);
    employee.setEmployeeId(userId);
    employee.setOrganizationName(organizationName);
    return employee;
  }
  
  // default role
  public static Role createRole() {
    return createRole(0L); 
  }
  
  public static Role createRole(long id) {
    return createRole(id, "test-role1");
  }
  
  public static Role createRole(long id, String name, boolean ...deleted) {
    Role role = new Role();
    role.setId(id);
    role.setName(name);
    role.setDeleted(deleted[0]);
    return role;
  }
}
