package com.smartreception.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartreception.entity.Role;
import com.smartreception.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
  
  @Autowired
  private RoleService roleService;
  
  @GetMapping
  public List<Role> getRoles() {
    return roleService.getRoles();
  }
 
}
