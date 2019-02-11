package com.smartreception.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visitor {
  private Long id;
  private String visitorType; //TODO: better to make it a separate entity
  private LocalDate arrivalDate;
  private LocalTime arrivalTime;
  private String email;
  private User host;  // host employee or company.
  private String note;
}