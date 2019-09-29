package com.smartreception.dao;

import java.util.List;

import com.smartreception.entity.Employee;

public interface EmployeeDao {
  public long insert(Employee employee);
  public Employee getEmployeeById(long id);
  public int update(Employee employee);
  public List<Employee> getAll();
  public void delete(long id);
}