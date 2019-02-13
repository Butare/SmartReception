package com.smartreception.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.jdbc.core.RowMapper;

import com.smartreception.entity.Visitor;

public class VisitorRowMapper implements RowMapper<Visitor>{

  @Override
  public Visitor mapRow(ResultSet rs, int rowNum) throws SQLException {
    Visitor visitor = new Visitor();
    visitor.setEmail(rs.getString("email"));
    //visitor.setHost();
    visitor.setNote(rs.getString("note"));
    visitor.setArrivalDate(LocalDate.parse(rs.getString("arrivalDate")));
    visitor.setArrivalTime(LocalTime.parse(rs.getString("arrivalTime")));
	return visitor;
  }

}
