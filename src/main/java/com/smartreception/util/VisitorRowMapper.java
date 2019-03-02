package com.smartreception.util;

import java.security.Timestamp;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.smartreception.entity.Visitor;

public class VisitorRowMapper implements RowMapper<Visitor>{

  @Override
  public Visitor mapRow(ResultSet rs, int rowNum) throws SQLException {
    Visitor mappedVisitor = BeanUtils.instantiateClass(Visitor.class);
    BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(mappedVisitor);
    bw.setAutoGrowNestedPaths(true);
    
    ResultSetMetaData meta = rs.getMetaData();
    for (int index = 0; index < meta.getColumnCount(); index++) {
      String column = JdbcUtils.lookupColumnName(meta, index);
      String field = snakeCaseToCamelCase(column.replaceAll(" ", ""));
      try {
        Object value = JdbcUtils.getResultSetValue(rs, index, Class.forName(meta.getColumnClassName(index)));
        if (!ObjectUtils.isEmpty(value)) {
          Class<?> clazz = value.getClass();
          
          if (clazz == Timestamp.class) {
            //TODO: remember to convert mysql timestamp when needed.
          }
        }
        bw.setPropertyValue(field, value);
      } catch (ClassNotFoundException e) {
        System.out.println(e.getMessage());
      }
    }
    
	return mappedVisitor;
  }
  
  
  public String snakeCaseToCamelCase(String name) {
    if (ObjectUtils.isEmpty(name)) {
      return null;
    }
    StringBuilder sb = new StringBuilder();
    for (String s : StringUtils.tokenizeToStringArray(name, "_")) {
      System.out.println(s);
      sb.append(sb.length() == 0 ? s : StringUtils.capitalize(s));
    }  
    return sb.toString();
  }

}
