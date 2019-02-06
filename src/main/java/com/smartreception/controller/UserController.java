package com.smartreception.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smartreception.entity.User;
import com.smartreception.service.UserService;

@RequestMapping(path = "/user")
@RestController
public class UserController {
  
  @Autowired
  private UserService userService;
  
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public User insert(@RequestBody @Valid User user) {
    long id = userService.insert(user);
    user.setId(id);
    return user;
  }
  
  @PutMapping("/{id}")
  public User update(@RequestBody @Valid User user, @PathVariable("id") long id) {
    userService.update(user, id);
    return userService.getUserById(id);
  }
}