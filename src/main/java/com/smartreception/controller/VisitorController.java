package com.smartreception.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartreception.entity.Visitor;
import com.smartreception.service.VisitorService;

@RestController
@RequestMapping("/visitors")
public class VisitorController {
  
  @Autowired
  private VisitorService visitorService;
  
  @GetMapping
  public List<Visitor> getAllVisitors() {
	return visitorService.getAll();
  }
}