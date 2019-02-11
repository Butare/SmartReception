package com.smartreception.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smartreception.dao.VisitorDao;
import com.smartreception.entity.Visitor;
import com.smartreception.service.VisitorService;

@Component
public class VisitorServiceImpl implements VisitorService {
  
  @Autowired
  private VisitorDao visitorDao;
  
  @Override
  public List<Visitor> getAll() {
	return visitorDao.getAll();
  }
}
