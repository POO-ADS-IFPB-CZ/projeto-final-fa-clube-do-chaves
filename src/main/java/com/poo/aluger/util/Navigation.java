package com.poo.aluger.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigation {

  public static void goToDashboard(Stage stage) {
    try {
      Parent root = FXMLLoader.load(Navigation.class.getResource("/com/poo/aluger/gui/dashboard.fxml"));
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void goTo(Stage stage, String path) {
    try {
      Parent root = FXMLLoader.load(Navigation.class.getResource(path));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

  public static void logout(Stage stage) {
    try {
      ProprietarioSingleton.getInstance().setProprietario(null);
      Parent root = FXMLLoader.load(Navigation.class.getResource("/com/poo/aluger/gui/TelaLogin.fxml"));
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
