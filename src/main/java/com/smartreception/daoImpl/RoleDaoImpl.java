package com.smartreception.daoImpl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
            .usingColumns("name", "createdAt", "updatedAt", "deleted")
              .usingGeneratedKeyColumns("id");
  }
  
  @Override
  public List<Role> getRoles() {
    return npJdbcTemplate.query("SELECT * FROM role WHERE deleted= FALSE", new RoleRowMapper());
  }

  @Override
  public Role getRoleById(long id) {
    String sql = "SELECT * FROM role WHERE id = :Id "; 
    
    try {
      return npJdbcTemplate.queryForObject(sql,
          Map.of("Id", id), new RoleRowMapper());
    }catch(Exception ex) { 
      return new Role();
     }
  }

  @Override
  public long insert(Role role) {
    return simpleJdbcInsert.executeAndReturnKey(
        new BeanPropertySqlParameterSource(role)).longValue();
  }

  @Override
  public long update(Role role) {

    StringBuilder updateQuery = new StringBuilder()
        .append("UPDATE role SET ")
        .append("name = :name, ")
        .append("createdAt = :createdAt, ")
        .append("updatedAt = :updatedAt, ")
        .append("deleted = :deleted ")
        .append("WHERE id = :Id");
    
    return npJdbcTemplate.update(updateQuery.toString(),
        new BeanPropertySqlParameterSource(role));
  }

  @Override
  public void delete(long id) {
    String deleteQuery = "UPDATE role SET deleted= TRUE WHERE id= :Id";
    npJdbcTemplate.update(deleteQuery, Map.of("Id", id));
  }
}
