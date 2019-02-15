package com.smartreception.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smartreception.entity.Visitor;
import com.smartreception.service.VisitorService;

@RestController
@RequestMapping("/visitors")
public class VisitorController {
  
  @Autowired
  private VisitorService visitorService;
  
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Visitor> getAllVisitors() {
    return visitorService.getAll();
  }
  
  @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Visitor insert(@RequestBody Visitor visitor) {
    long id = visitorService.insert(visitor);
    visitor.setId(id);
    return visitor;
  }
}