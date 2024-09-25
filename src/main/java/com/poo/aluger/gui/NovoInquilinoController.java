package com.poo.aluger.gui;

import com.poo.aluger.dao.InquilinoDao;
import com.poo.aluger.model.Inquilino;
import com.poo.aluger.model.Proprietario;
import com.poo.aluger.util.Navigation;
import com.poo.aluger.util.ProprietarioSingleton;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NovoInquilinoController {

  @FXML
  private TextField nome;

  @FXML
  private TextField cpf;

  @FXML
  private TextField telefone;

  @FXML
  private Button OkButton;

  @FXML
  public void initialize() {
    // Inicialização, se necessário
  }

  @FXML
  private void registrar() {
    try {
      String nomeInquilino = nome.getText();
      String cpfInquilino = cpf.getText();
      String telefoneInquilino = telefone.getText();

      Inquilino inquilino = new Inquilino(nomeInquilino, cpfInquilino, telefoneInquilino, null);
      Proprietario prop = ProprietarioSingleton.getInstance().getProprietario();

      int result = new InquilinoDao().insert(inquilino);
      inquilino.setCodigo(result);
      prop.addInquilino(inquilino);
      showAlert(Alert.AlertType.INFORMATION, "Success", "Inquilino registrado com sucesso!");
    } catch (Exception e) {
      showAlert(Alert.AlertType.ERROR, "Error", "Ocorreu um erro ao registrar o inquilino.");
    }
  }

  @FXML
  private void goback() {
    Navigation.goToDashboard((Stage) nome.getScene().getWindow());
  }

  @FXML
  private void logout() {
    Navigation.logout((Stage) nome.getScene().getWindow());
  }

  private void showAlert(Alert.AlertType alertType, String title, String content) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }
}
