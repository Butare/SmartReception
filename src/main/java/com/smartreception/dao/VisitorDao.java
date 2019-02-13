package com.smartreception.dao;

import java.util.List;

import com.smartreception.entity.Visitor;

public interface VisitorDao {
  public List<Visitor> getAll();
  public long insert(Visitor visitor);
}