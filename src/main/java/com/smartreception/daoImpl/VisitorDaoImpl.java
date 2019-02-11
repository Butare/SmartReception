package com.smartreception.daoImpl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.smartreception.dao.VisitorDao;
import com.smartreception.entity.Visitor;
import com.smartreception.util.VisitorRowMapper;

@Component
public class VisitorDaoImpl implements VisitorDao {

  private NamedParameterJdbcTemplate npJdbcTemplate;
  
  public VisitorDaoImpl(JdbcTemplate jdbcTemplate) {
	npJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
  }
  
  @Override
  public List<Visitor> getAll() {
    return npJdbcTemplate.query("SELECT * FROM visitor", new VisitorRowMapper());
  }

}
