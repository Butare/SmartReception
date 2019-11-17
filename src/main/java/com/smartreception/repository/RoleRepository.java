package com.smartreception.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.smartreception.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
  
  @Query(value = "select * from roles where name= :name")
  List<Role> findByName(@Param("name") String name);
}
