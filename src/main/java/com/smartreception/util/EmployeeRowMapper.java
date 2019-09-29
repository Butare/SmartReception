package com.smartreception.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smartreception.entity.Employee;

public class EmployeeRowMapper implements RowMapper<Employee>{

  @Override
  public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
    Employee employee = new Employee();
    employee.setId(rs.getLong("id"));
    employee.setFirstName(rs.getString("firstName"));
    employee.setLastName(rs.getString("lastName"));
    employee.setOrganizationName(rs.getString("organizationName"));
    employee.setEmail(rs.getString("email"));
    employee.setPhone(rs.getString("phone"));
    return employee;
  }
}
