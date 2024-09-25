package com.poo.aluger.gui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poo.aluger.dao.ProprietarioDao;
import com.poo.aluger.db.DBConnector;
import com.poo.aluger.model.Proprietario;
import com.poo.aluger.util.ProprietarioSingleton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaLoginController {
  @FXML
  private TextField email;
  @FXML
  private PasswordField senha;

  @FXML
  protected void goToTelaCadastro() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(TelaLoginController.class.getResource("TelaCadastro.fxml"));
    Parent root = fxmlLoader.load();
    Stage stage = (Stage) email.getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  protected void login() throws ClassNotFoundException, IOException {

    try (Connection con = DBConnector.getConnection()) {
      PreparedStatement pst = con
          .prepareStatement("SELECT P_Codigo FROM proprietario WHERE P_Email = ? AND P_Senha = ?");

      pst.setString(1, email.getText());
      pst.setString(2, senha.getText());

      ResultSet rs = pst.executeQuery();

      if (rs.next()) {
        int Codigo = rs.getInt("P_Codigo");
        Proprietario proprietario = new ProprietarioDao().findById(Codigo);
        proprietario.loadInfo();
        ProprietarioSingleton.getInstance().setProprietario(proprietario);
        loadDashboard();
      } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro de Login");
        alert.setHeaderText(null);
        alert.setContentText("Usuário ou senha inválidos!");

        alert.showAndWait();
        email.setText("");
        senha.setText("");
      }

    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }

  @FXML
  public void loadDashboard() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
    Parent root = fxmlLoader.load();

    Stage stage = (Stage) email.getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setTitle("Dashboard");
    stage.setScene(scene);
    stage.show();
  }

}
