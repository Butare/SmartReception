package com.smartreception.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smartreception.dao.RoleDao;
import com.smartreception.entity.Role;
import com.smartreception.service.RoleService;

@Component
public class RoleServiceImpl implements RoleService{
  
  @Autowired
  private RoleDao roleDao;

  @Override
  public List<Role> getRoles() {
    return roleDao.getRoles();
  }

  @Override
  public Role getRoleById() {
    return roleDao.getRoleById();
  }

  @Override
  public long insert(Role role) {
    return roleDao.insert(role);
  }

  @Override
  public long update(Role role, long roleId) {
    return roleDao.update(role, roleId);
  }

  @Override
  public void delete(long id) {
    roleDao.delete(id);
  }

}
