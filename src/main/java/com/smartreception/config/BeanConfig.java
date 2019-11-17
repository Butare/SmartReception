package com.smartreception.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jdbc.repository.RowMapperMap;
import org.springframework.data.jdbc.repository.config.ConfigurableRowMapperMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.smartreception.entity.Role;
import com.smartreception.util.RoleRowMapper;

@Configuration
public class BeanConfig {

  @Lazy
  @Bean
  public NamedParameterJdbcTemplate namedParameterJdbcTemplate(JdbcTemplate jdbcTemplate) {
    return new NamedParameterJdbcTemplate(jdbcTemplate);
  }
  
  @Bean
  RowMapperMap rowMappers() {
    return new ConfigurableRowMapperMap()
        .register(Role.class, new RoleRowMapper());
  }
}
