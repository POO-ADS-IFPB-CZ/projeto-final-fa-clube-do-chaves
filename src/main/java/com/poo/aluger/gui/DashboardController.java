package com.poo.aluger.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import javax.imageio.ImageIO;

import com.poo.aluger.model.Imovel;
import com.poo.aluger.model.Inquilino;
import com.poo.aluger.model.Manutencao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DashboardController {

  private Parent root;
  private Scene scene;
  private Stage stage;
  private FXMLLoader fxmlLoader;

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
    fxmlLoader = new FXMLLoader(DashboardController.class.getResource("houses.fxml"));
    root = fxmlLoader.load();

    HousesController controller = fxmlLoader.getController();
    InputStream imgStream = DashboardController.class.getResourceAsStream("/images/house.jpg");
    if (imgStream == null) {
      throw new IOException("Image file not found!");
    }
    BufferedImage img = ImageIO.read(imgStream);

    Imovel imovel = new Imovel(
        123,
        img,
        "Rua das Flores",
        123,
        "Jardim das Rosas",
        "Sao Paulo",
        "SP",
        "Casa",
        200.0,
        3,
        "Disponivel",
        2,
        "Casa com 3 quartos e 2 banheiros",
        null
    );

    controller.addImovel(imovel);
    controller.addImovel(imovel);
    controller.addImovel(imovel);

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setTitle("Houses");
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void inquilinos(ActionEvent event) throws IOException {
    fxmlLoader = new FXMLLoader(DashboardController.class.getResource("inquilinos.fxml"));
    root = fxmlLoader.load();

    InquilinosPageController controller = fxmlLoader.getController();
    controller.addInquilino(new Inquilino(1, "João", "123.123.123-12", "1234-1234", "1234-1234"));
    controller.addInquilino(new Inquilino(2, "Maria", "123.123.123-12", "1234-1234", "1234-1234"));
    controller.addInquilino(new Inquilino(3, "José", "123.123.123-12", "1234-1234", "1234-1234"));

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setTitle("Inquilinos");
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void manutencoes(ActionEvent event) throws IOException {
    fxmlLoader = new FXMLLoader(DashboardController.class.getResource("manutencaoScene.fxml"));
    root = fxmlLoader.load();

    ManutencaoSceneController controller = fxmlLoader.getController();
    Imovel imovel = new Imovel(
        123,
        null,
        "Rua das Flores",
        123,
        "Jardim das Rosas",
        "Sao Paulo",
        "SP",
        "Casa",
        200.0,
        3,
        "Disponivel",
        2,
        "Casa com 3 quartos e 2 banheiros",
        null
    );

    Manutencao manutencao = new Manutencao(1, "Pintura", LocalDate.of(2021, 1, 2), LocalDate.of(2023, 1, 2), 100.0, imovel);
    Manutencao manutencao2 = new Manutencao(2, "Pintura", LocalDate.of(2021, 1, 2), LocalDate.of(2021, 1, 4), 100.0, imovel);

    controller.addManutencao(manutencao);
    controller.addManutencao(manutencao2);

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setTitle("Manutenções");
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void pagamentos(ActionEvent event) throws IOException {
    fxmlLoader = new FXMLLoader(DashboardController.class.getResource("pagamentoScene.fxml"));
    root = fxmlLoader.load();

    PagamentoSceneController controller = fxmlLoader.getController();

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setTitle("Pagamentos");
    stage.setScene(scene);
    stage.show();
  }
}
