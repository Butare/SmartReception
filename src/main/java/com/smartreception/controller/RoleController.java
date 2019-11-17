package com.smartreception.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smartreception.entity.Role;
import com.smartreception.exception.NotFoundException;
import com.smartreception.repository.RoleRepository;
import com.smartreception.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

  private final RoleService roleService;
  
  @Autowired
  private RoleRepository roleRepository;
  
  @Autowired
  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }
  
  // test spring jdbc data
  public void printRoles() {
    System.out.println(roleRepository.findByName("tester1"));
  }

  @GetMapping
  public List<Role> getRoles() {
    printRoles();
    return roleService.getRoles();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Role insert(@RequestBody @Valid Role role) {
    long id = roleService.insert(role);
    role.setId(id);
    return role;
  }
  
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Role update(@PathVariable("id") long id, @RequestBody @Valid Role role) {
    Role originalRole = roleService.getRoleById(id);
    if (ObjectUtils.isEmpty(originalRole)) {
      throw new NotFoundException("Role id: "+ id +" doesn't exists.");
    }
    originalRole.setName(role.getName());
    roleService.update(originalRole);
    
    return originalRole;
  }
  
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") long id) {
    if (ObjectUtils.isEmpty(roleService.getRoleById(id))) {
      throw new NotFoundException("Role id: "+ id + " doesn't exists.");
    }
    roleService.delete(id);
  }
}
