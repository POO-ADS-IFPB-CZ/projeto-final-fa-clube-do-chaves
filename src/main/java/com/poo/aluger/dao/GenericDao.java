package com.poo.aluger.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {
  boolean insert(T entity) throws SQLException, IOException, ClassNotFoundException;

  boolean delete(int id) throws SQLException, IOException, ClassNotFoundException;

  boolean update(int id, T entity) throws SQLException, IOException, ClassNotFoundException;

  T findById(int id) throws SQLException, IOException, ClassNotFoundException;

  List<T> findAll() throws SQLException, IOException, ClassNotFoundException;
}
