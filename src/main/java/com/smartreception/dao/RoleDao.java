package com.smartreception.dao;

import java.util.List;

import com.smartreception.entity.Role;

public interface RoleDao {
  
  public List<Role> getRoles();
  public Role getRoleById(long id);
  public long insert(Role role);
  public long update(Role role);
  public void delete(long id); 
}
