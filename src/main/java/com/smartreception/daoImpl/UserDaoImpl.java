package com.smartreception.daoImpl;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.smartreception.dao.UserDao;
import com.smartreception.entity.User;
import com.smartreception.util.UserRowMapper;

@Component
public class UserDaoImpl implements UserDao {

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

  @Override
  public User getUserById(long id) {
   return npJdbcTemplate.queryForObject(
           "SELECT * FROM users WHERE id =:Id", Map.of("Id", id), new UserRowMapper());
  }

  @Override
  public int update(User user) {
    StringBuilder sqlBuilder = new StringBuilder()
        .append("UPDATE users SET ")
        .append("userId = :userId, ")
        .append("firstName = :firstName, ")
        .append("lastName = :lastName, ")
        .append("organizationName = :organizationName, ")
        .append("email = :email, ")
        .append("phone = :phone, ")
        .append("createdAt = :createdAt, ")
        .append("updatedAt = NOW() ")
        .append("WHERE id = :Id");
    
    return npJdbcTemplate.update(
        sqlBuilder.toString(),
        new BeanPropertySqlParameterSource(user));
  }
}
