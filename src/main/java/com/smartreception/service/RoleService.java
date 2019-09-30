package com.smartreception.service;

import java.util.List;

import com.smartreception.entity.Role;

public interface RoleService {
  
  public List<Role> getRoles();
  public Role getRoleById();
  public long insert(Role role);
  public long update(Role role, long roleId);
  public void delete(long id);
}
