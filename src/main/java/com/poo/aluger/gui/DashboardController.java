package com.poo.aluger.gui;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.poo.aluger.model.Imovel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Objects;

public class DashboardController {

  private Parent root;

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

  @FXML
  public void houses(ActionEvent event) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(DashboardController.class.getResource("houses.fxml"));
    Parent root = fxmlLoader.load();

    HousesController controller = fxmlLoader.getController();
    InputStream imgStream = DashboardController.class.getResourceAsStream("/images/house.jpg");
    if (imgStream == null) {
      throw new IOException("Image file not found!");
    }
    BufferedImage img = ImageIO.read(imgStream);
    Imovel imovel = new Imovel(1, img, "Casa", 12.2, 4, "alugada", 10,
        "casjdkhajkdha", 12.23, null, "SP", 123,
        "São Paulo", "Vila Mariana", "Rua Vergueiro");

    controller.addImovel(imovel);
    controller.addImovel(imovel);
    controller.addImovel(imovel);

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setTitle("Houses");
    stage.setScene(scene);
    stage.show();
  }

}
