package com.smartreception.daoImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.smartreception.dao.UserDao;
import com.smartreception.entity.User;

@Component
public class UserDaoImpl implements UserDao{

  private final SimpleJdbcInsert simpleJdbcInsert;
  private final NamedParameterJdbcTemplate npJdbcTemplate;
  
  public UserDaoImpl(JdbcTemplate jdbcTemplate) {
    this.npJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
    this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
        .withTableName("users")
        .usingColumns("userId", "firstName", "lastName", "organizationName", "email", "phone")
        .usingGeneratedKeyColumns("id");
  
  }
  
  @Override
  public long insert(User user) {
    return this.simpleJdbcInsert.executeAndReturnKey(
        new BeanPropertySqlParameterSource(user)).longValue();
  }
}
