package com.smartreception.entity;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  
  private Long id;
  
  @NotEmpty(message = "{notEmpty.user.userId}")
  private String userId;

  private String firstName;
  
  private String lastName;
  
  @NotEmpty(message = "{notEmpty.User.companyName}")
  private String companyName;
}
