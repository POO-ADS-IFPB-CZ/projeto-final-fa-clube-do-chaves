package com.poo.aluger.gui;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import com.poo.aluger.model.Inquilino;
import com.poo.aluger.util.ProprietarioSingleton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class InquilinosPageController {

  @FXML
  private VBox inquilinoContainer;

  public void addInquilino(Inquilino inquilino) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(HousesController.class.getResource("inquilino.fxml"));
    Node inquilinoNode = fxmlLoader.load();

    InquilinoController inquilinoController = fxmlLoader.getController();

    inquilinoController.setCodigo(inquilino.getCodigo() + "");
    inquilinoController.setNome(inquilino.getNome());
    inquilinoController.setCpf(inquilino.getCpf());
    inquilinoController.setTelefone1(inquilino.getTelefone1());
    inquilinoController.setTelefone2(inquilino.getTelefone2());

    inquilinoContainer.getChildren().add(inquilinoNode);
  }

  public void addInquilinos(List<Inquilino> inquilinos) throws IOException {
    for (Inquilino imovel : inquilinos) {
      addInquilino(imovel);
    }
  }

  public void initialize() {
    List<Inquilino> inquilinos = ProprietarioSingleton.getInstance().getProprietario().getInquilinos();
    try {
      addInquilinos(inquilinos);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

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
  public void goback(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setTitle("Dashboard");
    stage.setScene(scene);
    stage.show();
  }
}
