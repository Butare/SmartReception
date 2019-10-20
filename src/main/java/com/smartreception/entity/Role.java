package com.smartreception.entity;

import java.time.LocalDateTime;

import lombok.Data;


@Data
public class Role {
  
  private long id;
  private String name;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private boolean deleted;
  
  @Override
  public String toString() {
    
    return "[ "
        + " id: "+ this.id +", name: "+ this.name
        + ", createdAt: " + this.createdAt
        + ", updateAt: "+ this.updatedAt
        + ", deleted: "+ this.deleted
        + " ]";
  }
}
