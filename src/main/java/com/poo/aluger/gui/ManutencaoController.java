package com.poo.aluger.gui;

import java.time.LocalDate;

import com.poo.aluger.dao.ManutencaoDao;
import com.poo.aluger.util.Navigation;
import com.poo.aluger.util.ProprietarioSingleton;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ManutencaoController {

  @FXML
  private Label tipo, dataInicio, dataTermino, valor, codigo, codigoImovel;

  public void setCodigo(String codigo) {
    this.codigo.setText(codigo);
  }

  public void setTipo(String tipo) {
    this.tipo.setText(tipo);
  }

  public void setDataInicio(LocalDate dataInicio) {
    String data = dataInicio.getDayOfMonth() + "/" + dataInicio.getMonthValue() + "/" + dataInicio.getYear();
    this.dataInicio.setText(data);
  }

  public void setDataTermino(LocalDate dataTermino) {
    String data = dataTermino.getDayOfMonth() + "/" + dataTermino.getMonthValue() + "/" + dataTermino.getYear();
    this.dataTermino.setText(data);
  }

  public void setValor(String valor) {
    this.valor.setText(valor);
  }

  public void setCodigoImovel(String codigoImovel) {
    this.codigoImovel.setText(codigoImovel);
  }

  public void delete() {
    try {
      int cod = Integer.parseInt(this.codigo.getText());
      boolean result = new ManutencaoDao().delete(cod);
      if (result) {
        showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Manutenção deletada com sucesso!");
        ProprietarioSingleton.getInstance().getProprietario().removeManutencao(cod);
        Navigation.goToDashboard((Stage) valor.getScene().getWindow());
      } else {
        showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao deletar manutenção");
      }
    } catch (Exception e) {
      showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao deletar manutenção");
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
