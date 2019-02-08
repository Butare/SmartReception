package com.smartreception.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smartreception.entity.User;

public class UserRowMapper implements RowMapper<User>{

  @Override
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    User user = new User();
    user.setId(rs.getLong("id"));
    user.setFirstName(rs.getString("firstName"));
    user.setLastName(rs.getString("lastName"));
    user.setOrganizationName(rs.getString("organizationName"));
    user.setEmail(rs.getString("email"));
    user.setPhone(rs.getString("phone"));
    return user;
  }
}
