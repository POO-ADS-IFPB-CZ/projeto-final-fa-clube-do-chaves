package com.poo.aluger.gui;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

public class DashboardController {

  @FXML
  public void up(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("All Files", "*.*"));
    File file = fileChooser.showOpenDialog(null);
    if (file != null) {
      System.out.println("File chosen: " + file.getName());
    } else {
      System.out.println("File selection cancelled.");
    }
  }

  @FXML
  public void hello(ActionEvent event) throws IOException {
    System.out.println("hello");
  }
}
