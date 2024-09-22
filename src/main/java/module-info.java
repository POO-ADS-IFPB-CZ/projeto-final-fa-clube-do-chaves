module com.poo.aluger {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;
  requires org.postgresql.jdbc;
  requires java.desktop;

  opens com.poo.aluger.db to org.postgresql.jdbc;
  opens com.poo.aluger.gui to javafx.fxml;
  opens com.poo.aluger.util to javafx.fxml;

  exports com.poo.aluger.gui;
  exports com.poo.aluger.dao;
  exports com.poo.aluger.util;
  exports com.poo.aluger.model;
}
