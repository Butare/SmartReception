package com.smartreception.serviceImpl;

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
  public long insert(Employee user) {
	if (ObjectUtils.isEmpty(user)) {
	  throw new NotFoundException("User should be specified.");
	}
    return employeeDao.insert(user);
  }

  @Override
  public void delete(long id) {
    checkUserExists(id);
    employeeDao.delete(id);
  }

  @Override
  public int update(Employee user, long id) {
	if (ObjectUtils.isEmpty(user)) {
	  throw new NotFoundException("User should be specified.");
	}
    checkUserExists(id);
    user.setId(id);
    return employeeDao.update(user);
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
      throw new NotFoundException("User does not exist. id = "+ id);
    }
  }
  
}
