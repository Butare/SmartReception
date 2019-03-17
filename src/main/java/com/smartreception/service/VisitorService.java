package com.smartreception.service;

import java.util.List;

import com.smartreception.entity.Visitor;

public interface VisitorService {
  public List<Visitor> getAll();
  public long insert(Visitor visitor);
  public int update(long id, Visitor visitor);
  public Visitor getVisitorById(long id);
}
