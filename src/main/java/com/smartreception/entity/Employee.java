package com.smartreception.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class Employee {
  
  private long id;
  
  @NotEmpty(message = "{notEmpty.user.userId}")
  private String userId;
  
  private String firstName;
  
  private String lastName;
    
  @NotEmpty(message = "{notEmpty.User.organizationName}")
  private String organizationName;
  
  private String email;
  
  private String phone;
  
  private Boolean deleted;
  
  private LocalDateTime createdAt;
  
  private LocalDateTime updatedAt;

}