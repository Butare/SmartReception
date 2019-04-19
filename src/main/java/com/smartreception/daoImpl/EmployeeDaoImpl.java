package com.smartreception.daoImpl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.smartreception.dao.EmployeeDao;
import com.smartreception.entity.Employee;
import com.smartreception.util.EmployeeRowMapper;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

  /**
   * The SimpleJdbcInsert
   */
  private final SimpleJdbcInsert simpleJdbcInsert;
  
  /**
   * The NamedParameterJdbcTemplate
   */
  private final NamedParameterJdbcTemplate npJdbcTemplate;
  
  public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
    this.npJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
    this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
        .withTableName("employees")
        .usingColumns("employeeId", "firstName", "lastName", "organizationName", "email", "phone", "createdAt", "updatedAt")
        .usingGeneratedKeyColumns("id");
  }
  
  @Override
  public long insert(Employee employee) {
    return this.simpleJdbcInsert.executeAndReturnKey(
        new BeanPropertySqlParameterSource(employee)).longValue();
  }

  @Override
  public Employee getEmployeeById(long id) {
    try {
      return npJdbcTemplate.queryForObject(
           "SELECT * FROM employees WHERE id = :Id", Map.of("Id", id), new EmployeeRowMapper());
    }catch(RuntimeException ex) {
      return null;
    }
  }

  @Override
  public int update(Employee employee) {
    StringBuilder sqlBuilder = new StringBuilder()
        .append("UPDATE employees SET ")
        .append("employeeId = :employeeId, ")
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
        new BeanPropertySqlParameterSource(employee));
  }

  @Override
  public List<Employee> getAll() {
	  return npJdbcTemplate.query("SELECT * FROM employees WHERE deleted = FALSE", new EmployeeRowMapper());
  }

  @Override
  public void delete(long id) {
    String sqlQuery = "UPDATE employees SET deleted = TRUE WHERE id = :Id";
    npJdbcTemplate.update(sqlQuery, Map.of("Id", id));
  }
  
}
