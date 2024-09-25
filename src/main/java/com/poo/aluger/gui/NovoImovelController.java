package com.poo.aluger.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import javax.imageio.ImageIO;

import com.poo.aluger.dao.ImovelDao;
import com.poo.aluger.model.Imovel;
import com.poo.aluger.model.Proprietario;
import com.poo.aluger.util.ProprietarioSingleton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NovoImovelController {

  @FXML
  private ComboBox<String> estadoField, tipoImovelField;

  @FXML
  private TextField cidadeField, ruaField, bairroField, numeroFieldEndereco, areaTotalField, numeroQuartosField, numeroBanheirosField;

  @FXML
  private TextArea descricaoTextArea;

  @FXML
  private Label fotoLabel;

  @FXML
  public void initialize() {
    estadoField.getItems().addAll(
            "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
    );

    tipoImovelField.getItems().addAll(
            "Apartamento", "Casa", "Chácara", "Cobertura", "Condomínio", "Duplex", "Flat", "Kitnet", "Loft", "Sobrado", "Studio"
    );
  }

  @FXML
  public void goback() throws IOException {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
    Stage stage = (Stage) fotoLabel.getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setTitle("Dashboard");
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void logout() {
    System.out.println("logout");
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
      alert.setContentText("Por favor, insira valores numéricos válidos para número, quantidade de quartos, quantidade de banheiros e área total.");
      alert.showAndWait();
      return;
    }

    Imovel imovel = new Imovel(foto, rua, numero, bairro, cidade, estado, tipo, areaTotal, quantidadeQuartos, "livre",
            quantidadeBanheiros, descricao, proprietario);
    Alert alert;
    if (new ImovelDao().insert(imovel)) {
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
