package com.smartreception.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smartreception.entity.Employee;
import com.smartreception.service.EmployeeService;

@RequestMapping(path = "/employees")
@RestController
public class EmployeeController {
  
  private final EmployeeService employeeService;
  
  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }
  
  //TODO: update this to get current user, or removed it before production
  @GetMapping("/me")
  public String getMe() {
    return "Its me \n";
  }
  
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Employee insert(@RequestBody @Valid Employee employee) {
    long id = employeeService.insert(employee);
    employee.setId(id);
    return employee;
  }
  
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Employee update(@RequestBody @Valid Employee employee, @PathVariable("id") long id) {
    employeeService.update(employee, id);
    return employeeService.getEmployeeById(id);
  }
  
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Employee> getAll() {
    return employeeService.getAll();
  }
  
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") long id) {
    employeeService.delete(id);
  }
}