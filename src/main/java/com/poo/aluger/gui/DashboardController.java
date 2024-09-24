package com.poo.aluger.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.poo.aluger.model.Proprietario;
import com.poo.aluger.util.ProprietarioSingleton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DashboardController {

  private Parent root;
  private Scene scene;
  private Stage stage;
  private FXMLLoader fxmlLoader;

  @FXML
  private Text imoveisQtd, inquilinosQtd, manutencoesQtd, pagamentosQtd, contratosQtd, saldoVal, nomeProp;

  public void initialize() {
    Proprietario proprietario = ProprietarioSingleton.getInstance().getProprietario();
    if (proprietario != null) {
      imoveisQtd.setText(String.valueOf(proprietario.getQtdImoveis()));
      inquilinosQtd.setText(String.valueOf(proprietario.getQtdInquilinos()));
      manutencoesQtd.setText(String.valueOf(proprietario.getQtdManutencoes()));
      pagamentosQtd.setText(String.valueOf(proprietario.getQtdPagamentos()));
      contratosQtd.setText(String.valueOf(proprietario.getQtdContratos()));
      saldoVal.setText(String.valueOf(proprietario.getSaldo()));
      nomeProp.setText(proprietario.getNome());
    } else {
      System.err.println("Proprietario is null");
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
  public void houses(ActionEvent event) throws IOException {
    fxmlLoader = new FXMLLoader(DashboardController.class.getResource("houses.fxml"));
    root = fxmlLoader.load();

    HousesController controller = fxmlLoader.getController();
    controller.initialize();
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
    controller.initialize();
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
    controller.initialize();

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
    controller.initialize();

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setTitle("Pagamentos");
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void logout(ActionEvent event) throws IOException {
    ProprietarioSingleton.getInstance().setProprietario(null);
    fxmlLoader = new FXMLLoader(DashboardController.class.getResource("TelaLogin.fxml"));
    root = fxmlLoader.load();
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setTitle("Login");
    stage.setScene(scene);
    stage.show();
  }
}
