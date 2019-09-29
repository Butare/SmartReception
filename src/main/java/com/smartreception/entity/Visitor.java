package com.smartreception.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.smartreception.ContextHelper;
import com.smartreception.dao.EmployeeDao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class Visitor {
  private Long id;
  private String type; //TODO: better to make it a separate entity
  private LocalDate arrivalDate;
  private LocalTime arrivalTime;
  private String email; // for front-desk clerk
  private Long hostId;  // host employee or company.
  private Employee employee;  // employee ref.
  private String note;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Long createdBy;
  
  public Employee getEmployee() {
    if (employee != null) {
      return employee;
    }
    EmployeeDao dao = ContextHelper.getContext().getBean(EmployeeDao.class);
    this.employee = dao.getEmployeeById(this.hostId);
    return this.employee;
  }
}