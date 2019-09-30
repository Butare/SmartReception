package com.smartreception.dao;

import java.util.List;

import com.smartreception.entity.Role;

public interface RoleDao {
  
  public List<Role> getRoles();
  public Role getRoleById();
  public long insert(Role role);
  public long update(Role role, long roleId);
  public void delete(long id);
}
