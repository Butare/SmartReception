package com.smartreception.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;


@Data
@Table("roles")
public class Role {
  
  @Id
  private long id;
  private String name;
  @Column("createdAt")
  private LocalDateTime createdAt;
  @Column("updatedAt")
  private LocalDateTime updatedAt;
  
  @Column(keyColumn = "TINYINT")
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
  
  public Role() {}
  private Role(String name, LocalDateTime createdAt) {
    this.name = name;
    this.createdAt = createdAt;
  }

  public static Role create(String name, LocalDateTime createdAt) {
    return new Role(name, createdAt);
  }
}
