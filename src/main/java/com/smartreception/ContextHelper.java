package com.smartreception;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ContextHelper {
  
  @Autowired
  private ApplicationContext context;
  
  private static ContextHelper singleton;
  
  @PostConstruct
  private void setup() {
    singleton = this;
  }
  
  public static ApplicationContext getContext() {
    return singleton.context;
  }
}
