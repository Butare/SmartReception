package com.smartreception.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.smartreception.entity.Role;

public class RoleRowMapper implements RowMapper<Role>{

  @Override
  public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
    Role role = new Role();
    role.setId(rs.getLong("id"));
    role.setName(rs.getString("name"));
    role.setCreatedAt(rs.getObject("createdAt", LocalDateTime.class));
    
    return role;
  }

}
