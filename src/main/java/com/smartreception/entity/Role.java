package com.smartreception.entity;

import java.time.LocalDateTime;

import lombok.Data;


@Data
public class Role {
  
  private long id;
  private String name;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
