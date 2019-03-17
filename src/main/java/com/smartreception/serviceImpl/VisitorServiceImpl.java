package com.smartreception.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.smartreception.dao.VisitorDao;
import com.smartreception.entity.Visitor;
import com.smartreception.exception.NotFoundException;
import com.smartreception.service.VisitorService;

@Component
public class VisitorServiceImpl implements VisitorService {
  
  @Autowired
  private VisitorDao visitorDao;
  
  @Override
  public List<Visitor> getAll() {
    return visitorDao.getAll();
  }

  @Override
  public long insert(Visitor visitor) {
    visitor.setCreatedAt(LocalDateTime.now());
    visitor.setCreatedBy(visitor.getUser().getId());
    return visitorDao.insert(visitor);
  }

  @Override
  public int update(long id, Visitor visitor) {
	checkVisitor(id);	
	return visitorDao.update(visitor);
  }

  @Override
  public Visitor getVisitorById(long id) {
	return visitorDao.getVisitorById(id);
  }

  @Override
  public void delete(long id) {
    checkVisitor(id);	
    visitorDao.delete(id);
  }
  
  private void checkVisitor(long id) {
	if (ObjectUtils.isEmpty(visitorDao.getVisitorById(id))) {
	  throw new NotFoundException(String.format("User id = %d, does not exists.", id));
	}
  }
  
}