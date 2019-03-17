package com.smartreception.daoImpl;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.smartreception.dao.VisitorDao;
import com.smartreception.entity.Visitor;
import com.smartreception.util.VisitorRowMapper;

@Component
public class VisitorDaoImpl implements VisitorDao {

  /**
   * The Named Parameter JdbcTemplate
   */
  private NamedParameterJdbcTemplate npJdbcTemplate;
  
  /**
   * The Simple Jdbc Insert
   */
  private SimpleJdbcInsert simpleJdbcInsert;
  
  public VisitorDaoImpl(JdbcTemplate jdbcTemplate) {
    simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
        .withTableName("visitors")
        .usingColumns("type", "arrivalDate", "arrivalTime", "hostId", "note", "createdAt", "createdBy")
        .usingGeneratedKeyColumns("id");
    
    npJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
  }
  
  @Override
  public List<Visitor> getAll() {
    return npJdbcTemplate.query("SELECT * FROM visitors", new VisitorRowMapper());
  }

  @Override
  public long insert(Visitor visitor) {
    return simpleJdbcInsert.executeAndReturnKey(
            new BeanPropertySqlParameterSource(visitor)).longValue();
  }
  
  @Override
  public int update(Visitor visitor) {
	StringBuilder sqlBuilder = new StringBuilder()
		.append("UPDATE visitors SET ")
		.append("type = :type, ")
		.append("arrivalDate = :arrivalDate, ")
		.append("arrivalTime = :arrivalTime, ")
		.append("hostId = :hostId, ")
		.append("user = :user, ")
		.append("note = :note, ")
		.append("updatedAt = NOW() ")
		.append("WHERE id= :Id");
		
	return npJdbcTemplate.update(sqlBuilder.toString(), new BeanPropertySqlParameterSource(visitor));
  }

  @Override
  public Visitor getVisitorById(long id) {
	try {
	  return npJdbcTemplate.queryForObject(
		  "SELECT * FROM visitors WHERE id= :Id", Map.of("Id", id), new VisitorRowMapper());
	}catch(DataAccessException dae) {
	  return null;
	}
  }

  @Override
  public void delete(long id) {
    npJdbcTemplate.update(
    	"UPDATE visitors SET deleted = TRUE, updatedAt = NOW() WHERE id = :Id",
    	Map.of("Id", id));
  }

}
