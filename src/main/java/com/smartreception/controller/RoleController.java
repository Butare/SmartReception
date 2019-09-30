package com.smartreception.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartreception.entity.Role;

@RestController
@RequestMapping("/roles")
public class RoleController {
  
  @GetMapping
  public List<Role> getRoles() {
    List<Role> roles = new ArrayList<Role>();
        roles.add(getDummyRole());
        
     return roles;
  }
  
  
  private Role getDummyRole() {
    Role role = new Role();
    role.setId(13);
    role.setName("Admin");
    role.setCreatedAt(LocalDateTime.now());
    return role;
  }
}
