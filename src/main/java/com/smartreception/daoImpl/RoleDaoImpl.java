package com.smartreception.daoImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.smartreception.dao.RoleDao;
import com.smartreception.entity.Role;
import com.smartreception.util.RoleRowMapper;

@Component
public class RoleDaoImpl implements RoleDao {

  /**
   * The SimpleJdbcInsert
   */
  private final SimpleJdbcInsert simpleJdbcInsert;
  
  /**
   * The NamedParameterJdbcTemplate
   */
  private final NamedParameterJdbcTemplate npJdbcTemplate;
  

  public RoleDaoImpl(JdbcTemplate jdbcTemplate) {
    this.npJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
    this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource());
    
    this.simpleJdbcInsert
          .withTableName("role")
            .usingColumns("id", "name", "createdAt", "updatedAt")
              .usingGeneratedKeyColumns("id");
  }
  
  @Override
  public List<Role> getRoles() {
    return npJdbcTemplate.query("SELECT * FROM role", new RoleRowMapper());
  }

  @Override
  public Role getRoleById() {
    return null;
  }

  @Override
  public long insert(Role role) {
    return 0;
  }

  @Override
  public long update(Role role, long roleId) {
    return 0;
  }

  @Override
  public void delete(long id) {
    
  }
  
  private Role getDummyRole() {
    Role role = new Role();
    role.setId(20);
    role.setName("empty");
    role.setCreatedAt(LocalDateTime.now());
    return role;
  }

}
