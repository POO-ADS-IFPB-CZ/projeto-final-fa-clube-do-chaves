module com.poo.aluger {
  requires javafx.controls;
  requires javafx.fxml;

  opens com.poo.aluger.gui to javafx.fxml;

  exports com.poo.aluger.gui;
  exports com.poo.aluger.dao;
  exports com.poo.aluger.util;
  exports com.poo.aluger.model;
}
