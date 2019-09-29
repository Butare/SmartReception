package com.smartreception.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.smartreception.dao.EmployeeDao;
import com.smartreception.entity.Employee;
import com.smartreception.exception.NotFoundException;
import com.smartreception.service.EmployeeService;

@Component
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeDao employeeDao;

  @Override
  public long insert(Employee employee) {
    if (ObjectUtils.isEmpty(employee)) {
      throw new NotFoundException("User should be specified.");
    }
    employee.setCreatedAt(LocalDateTime.now());
    employee.setDeleted(false);
    return employeeDao.insert(employee);
  }

  @Override
  public void delete(long id) {
    checkUserExists(id);
    employeeDao.delete(id);
  }

  @Override
  public int update(Employee employee, long id) {
    if (ObjectUtils.isEmpty(employee)) {
      throw new NotFoundException("User should be specified.");
    }
    checkUserExists(id);
    employee.setId(id);
    return employeeDao.update(employee);
  }

  @Override
  public Employee getEmployeeById(long id) {
    checkUserExists(id);
    return employeeDao.getEmployeeById(id);
  }

  @Override
  public List<Employee> getAll() {
    return employeeDao.getAll();
  }

  private void checkUserExists(long id) {
    if (ObjectUtils.isEmpty(employeeDao.getEmployeeById(id))) {
      throw new NotFoundException("User does not exist. id = " + id);
    }
  }

}
