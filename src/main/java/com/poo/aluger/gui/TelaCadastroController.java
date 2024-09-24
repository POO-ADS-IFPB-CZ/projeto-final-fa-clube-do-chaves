package com.poo.aluger.gui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import com.poo.aluger.dao.ProprietarioDao;
import com.poo.aluger.db.DBConnector;
import com.poo.aluger.model.Proprietario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCadastroController {
  @FXML
  private TextField nome;
  @FXML
  private TextField email;
  @FXML
  private PasswordField senha;

  private ProprietarioDao dao = new ProprietarioDao();

  @FXML
  protected void cadastrar() throws IOException {
    try (Connection con = DBConnector.getConnection()) {
      Proprietario prop = dao.findByEmail(email.getText());

      if (prop != null) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro de Login");
        alert.setHeaderText(null);
        alert.setContentText("Usuário já cadastrado!");

        alert.showAndWait();
        email.setText("");
        senha.setText("");
        nome.setText("");
      } else {
        Proprietario proprietario = new Proprietario(nome.getText(), email.getText(), senha.getText());
        dao.insert(proprietario);
        System.out.println("Usuário cadastrado com sucesso!");
        loadDashboard();
      }
    } catch (SQLException | ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }
}

  @FXML
  protected void irParaTelaLogin() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(TelaCadastroController.class.getResource("TelaLogin.fxml"));
    Parent root = fxmlLoader.load();
    Stage stage = (Stage) nome.getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void loadDashboard() throws IOException {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
    Stage stage = (Stage) nome.getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setTitle("Dashboard");
    stage.setScene(scene);
    stage.show();
  }
  
}
