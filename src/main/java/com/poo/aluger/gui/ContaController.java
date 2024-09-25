package com.poo.aluger.gui;

import java.io.IOException;
import java.sql.SQLException;

import com.poo.aluger.dao.ProprietarioDao;
import com.poo.aluger.model.Proprietario;
import com.poo.aluger.util.Navigation;
import com.poo.aluger.util.ProprietarioSingleton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ContaController {

  @FXML
  private Button logoutButton;

  @FXML
  private Button contaButton;

  @FXML
  private Button gobackButton;

  @FXML
  private Text nome;

  @FXML
  private Text email;

  @FXML
  private PasswordField senhaPasswordField; // PasswordField for hidden password

  @FXML
  private TextField senhaTextField; // TextField for visible password

  @FXML
  private void initialize() {
    Proprietario pr = ProprietarioSingleton.getInstance().getProprietario();
    nome.setText(pr.getNome());
    email.setText(pr.getEmail());
    senhaTextField.setText(pr.getSenha());
  }

  // @FXML
  // private void mostrarSenha(ActionEvent event) {
  //   // Toggle between showing and hiding the password
  //   if (senhaVisivel) {
  //     // Hide password (show PasswordField, hide TextField)
  //     senhaTextField.setVisible(false);
  //     senhaTextField.setManaged(false); // Hide from layout
  //     senhaPasswordField.setText(senhaTextField.getText()); // Sync text
  //     senhaPasswordField.setVisible(true);
  //     senhaPasswordField.setManaged(true); // Ensure it is part of layout
  //     mostrarSenhaButton.setText("Mostrar");
  //   } else {
  //     // Show password (hide PasswordField, show TextField)
  //     senhaTextField.setText(senhaPasswordField.getText()); // Sync text
  //     senhaPasswordField.setVisible(false);
  //     senhaPasswordField.setManaged(false); // Hide from layout
  //     senhaTextField.setVisible(true);
  //     senhaTextField.setManaged(true); // Ensure it is part of layout
  //     mostrarSenhaButton.setText("Ocultar");
  //   }
  //   senhaVisivel = !senhaVisivel;
  // }

  @FXML
  private void salvar(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
    String senha = senhaTextField.getText();
    Proprietario novo = new Proprietario(nome.getText(), email.getText(), senha);
    boolean result = new ProprietarioDao().update(ProprietarioSingleton.getInstance().getProprietario().getCodigo(), novo);
    if (result) {
      this.showAlert(AlertType.INFORMATION, "Sucesso", "Senha alterada com sucesso");
      ProprietarioSingleton.getInstance().setProprietario(novo);
      Navigation.goToDashboard((Stage) this.email.getScene().getWindow());
    } else {
      this.showAlert(AlertType.ERROR, "Erro", "Erro ao alterar senha");
    }
    ProprietarioSingleton.getInstance().setProprietario(novo);

    System.out.println("Alterações salvas.");
  }

  private void showAlert(Alert.AlertType alertType, String title, String content) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText((String) null);
    alert.setContentText(content);
    alert.showAndWait();
  }

  @FXML
  private void goback() {
    Navigation.goToDashboard((Stage) this.email.getScene().getWindow());
  }

  @FXML
  private void logout() {
    Navigation.logout((Stage) this.email.getScene().getWindow());
  }
}
