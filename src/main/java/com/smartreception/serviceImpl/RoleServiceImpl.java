package com.smartreception.serviceImpl;

import java.time.LocalDateTime;
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
  public Role getRoleById(long id) {
    return roleDao.getRoleById(id);
  }

  @Override
  public long insert(Role role) {
    role.setCreatedAt(LocalDateTime.now());
    return roleDao.insert(role);
  }

  @Override
  public long update(Role role) {
    role.setUpdatedAt(LocalDateTime.now());
    return roleDao.update(role);
  }

  @Override
  public void delete(long id) {
    roleDao.delete(id);
  }

}
