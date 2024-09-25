package com.poo.aluger.gui;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import com.poo.aluger.model.Imovel;
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

public class HousesController {

  @FXML
  private VBox imovelContainer; // VBox where items will be added dynamically

  public void initialize() {
    List<Imovel> imovels = ProprietarioSingleton.getInstance().getProprietario().getImoveis();
    try {
      addMultipleImoveis(imovels);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void addImovel(Imovel imovel) throws IOException {
    // Carregar o fxml do imovel
    FXMLLoader fxmlLoader = new FXMLLoader(HousesController.class.getResource("imovel.fxml"));
    Node imovelNode = fxmlLoader.load();

    // Obter o controlador associado ao imovel.fxml
    ImovelController imovelController = fxmlLoader.getController();

    // Atualizar os valores no controlador
    imovelController.setTipo(imovel.getTipo());
    imovelController.setImage(imovel.getFoto());
    imovelController.setEstado(imovel.getEstado());
    imovelController.setCidade(imovel.getCidade());
    imovelController.setBairro(imovel.getBairro());
    imovelController.setRua(imovel.getRua());
    imovelController.setNumero(String.valueOf(imovel.getNumero()));
    imovelController.setCodigo(String.valueOf(imovel.getCodigo()));
    imovelController.setAreaTotal(String.valueOf(imovel.getAreaTotal()));
    imovelController.setQuantidadeQuartos(String.valueOf(imovel.getQuantidadeQuartos()));
    imovelController.setQuantidadeBanheiros(String.valueOf(imovel.getQuantidadeBanheiros()));
    imovelController.setStatus(imovel.getStatus());

    // Adicionar o novo imovel ao VBox
    imovelContainer.getChildren().add(imovelNode);
  }

  public void addMultipleImoveis(List<Imovel> imoveis) throws IOException {
    for (Imovel imovel : imoveis) {
      addImovel(imovel);
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
  public void goback(ActionEvent event) throws IOException {
    System.out.println("goback");
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setTitle("Dashboard");
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void addImovel(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("novoImovel.fxml")));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setTitle("Adicionar Imóvel");
    stage.setScene(scene);
    stage.show();
  }
}
