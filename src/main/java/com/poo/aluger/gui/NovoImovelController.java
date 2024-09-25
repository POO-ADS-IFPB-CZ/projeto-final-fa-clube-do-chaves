package com.poo.aluger.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import com.poo.aluger.dao.ImovelDao;
import com.poo.aluger.model.Imovel;
import com.poo.aluger.model.Proprietario;
import com.poo.aluger.util.Navigation;
import com.poo.aluger.util.ProprietarioSingleton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NovoImovelController {

  @FXML
  private ComboBox<String> estadoField, tipoImovelField;

  @FXML
  private TextField cidadeField, ruaField, bairroField, numeroFieldEndereco, areaTotalField, numeroQuartosField,
      numeroBanheirosField;

  @FXML
  private TextArea descricaoTextArea;

  @FXML
  private Label fotoLabel;

  @FXML
  public void initialize() {
    estadoField.getItems().addAll(
        "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI",
        "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");

    tipoImovelField.getItems().addAll(
        "Apartamento", "Casa", "Chácara", "Cobertura", "Condomínio", "Duplex", "Flat", "Kitnet", "Loft", "Sobrado",
        "Studio");
  }

  @FXML
  public void logout() {
    Navigation.logout((Stage) fotoLabel.getScene().getWindow());
  }

  @FXML
  public void openFileChooser() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
      fotoLabel.setText(selectedFile.getAbsolutePath());
    }
  }

  @FXML
  public void goback() {
    Navigation.goToDashboard((Stage) fotoLabel.getScene().getWindow());
  }

  @FXML
  public void cadastrar() throws SQLException, IOException, ClassNotFoundException {
    String estado = estadoField.getValue();
    String cidade = cidadeField.getText();
    String rua = ruaField.getText();
    String bairro = bairroField.getText();
    String fotoPath = fotoLabel.getText();
    BufferedImage foto;
    if (fotoPath.isEmpty())
      foto = null;
    else
      foto = ImageIO.read(new File(fotoPath));

    String tipo = tipoImovelField.getValue();
    String descricao = descricaoTextArea.getText();
    Proprietario proprietario = ProprietarioSingleton.getInstance().getProprietario();

    int numero, quantidadeQuartos, quantidadeBanheiros;
    double areaTotal;

    try {
      numero = Integer.parseInt(numeroFieldEndereco.getText());
      quantidadeQuartos = Integer.parseInt(numeroQuartosField.getText());
      quantidadeBanheiros = Integer.parseInt(numeroBanheirosField.getText());
      areaTotal = Double.parseDouble(areaTotalField.getText());
    } catch (NumberFormatException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText(
          "Por favor, insira valores numéricos válidos para número, quantidade de quartos, quantidade de banheiros e área total.");
      alert.showAndWait();
      return;
    }

    Imovel imovel = new Imovel(foto, rua, numero, bairro, cidade, estado, tipo, areaTotal, quantidadeQuartos, "livre",
        quantidadeBanheiros, descricao, proprietario);
    Alert alert;
    int result = new ImovelDao().insert(imovel);
    if (result > 0) {
      imovel.setCodigo(result);
      proprietario.addImovel(imovel);
      alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Success");
      alert.setHeaderText(null);
      alert.setContentText("Imovel cadastrado com sucesso!");
      alert.showAndWait();
      goback();
    } else {
      alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText("Falha ao cadastrar imovel!");
      alert.showAndWait();
    }
  }
}
