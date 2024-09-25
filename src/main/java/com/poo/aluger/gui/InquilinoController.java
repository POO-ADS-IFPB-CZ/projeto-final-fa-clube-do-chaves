package com.poo.aluger.gui;

import java.io.IOException;
import java.sql.SQLException;

import com.poo.aluger.dao.InquilinoDao;
import com.poo.aluger.util.Navigation;
import com.poo.aluger.util.ProprietarioSingleton;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class InquilinoController {

  @FXML
  private Label codigo, nome, cpf, telefone1, telefone2;

  public void setCodigo(String codigo) {
    this.codigo.setText(codigo);
  }

  public void setNome(String nome) {
    this.nome.setText(nome);
  }

  public void setCpf(String cpf) {
    this.cpf.setText(cpf);
  }

  public void setTelefone1(String telefone1) {
    this.telefone1.setText(telefone1);
  }

  public void setTelefone2(String telefone2) {
    this.telefone2.setText(telefone2);
  }

  // na verdade é deletar :)
  @FXML
  public void delete() throws ClassNotFoundException, SQLException, IOException {
    int cod = Integer.parseInt(this.codigo.getText());
    boolean result = new InquilinoDao().delete(cod);
    if (result) {
      showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Inquilino deletado com sucesso!");
      ProprietarioSingleton.getInstance().getProprietario().removeInquilino(cod);
      Navigation.goToDashboard((Stage) codigo.getScene().getWindow());
    } else {
      showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao deletar imóvel");
    }
  }

  private void showAlert(Alert.AlertType alertType, String title, String content) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }

}
