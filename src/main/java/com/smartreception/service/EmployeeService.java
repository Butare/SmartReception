package com.smartreception.service;

import java.util.List;

import com.smartreception.entity.Employee;

public interface EmployeeService {
  public long insert(Employee employee);
  public void delete(long id);
  public int update(Employee employee, long id);
  public Employee getEmployeeById(long id);
  public List<Employee> getAll();
}