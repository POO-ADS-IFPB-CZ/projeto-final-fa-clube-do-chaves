package com.poo.aluger.gui;

import java.io.IOException;
import java.sql.SQLException;

import com.poo.aluger.dao.PagamentoDao;
import com.poo.aluger.util.Navigation;
import com.poo.aluger.util.ProprietarioSingleton;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PagamentoController {

  @FXML
  private Label valor;

  @FXML
  private Label forma;

  @FXML
  private Label data;

  @FXML
  private Label codigoContrato;

  @FXML
  private Label codigo;

  // Method to initialize the controller
  @FXML
  public void initialize() {
    // Initialization logic, if needed
  }

  public void setPaymentDetails(String valor, String forma, String data, String codigoContrato, String codigo) {
    this.valor.setText(valor);
    this.forma.setText(forma);
    this.data.setText(data);
    this.codigoContrato.setText(codigoContrato);
    this.codigo.setText(codigo);
  }

  @FXML
  public void delete() throws ClassNotFoundException, SQLException, IOException {
    int cod = Integer.parseInt(this.codigo.getText());
    boolean result = new PagamentoDao().delete(cod);
    if (result) {
      showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Pagamento deletado com sucesso!");
      ProprietarioSingleton.getInstance().getProprietario().removePagamento(cod);
      Navigation.goToDashboard((Stage) codigo.getScene().getWindow());
    } else {
      showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao deletar pagamento");
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
