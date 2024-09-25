package com.poo.aluger.gui;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import com.poo.aluger.dao.ContratoAluguelDao;
import com.poo.aluger.util.Navigation;
import com.poo.aluger.util.ProprietarioSingleton;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ContratoAluguelController {

    @FXML
    private Label codigo, dataInicio, dataTermino, valorAluguel, diaPagamento, inquilino, imovel, proprietario;

    public void setCodigo(String codigo) {
        this.codigo.setText(codigo);
    }
  
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio.setText(dataInicio.toString());
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino.setText(dataTermino.toString());
    }

    public void setValorAluguel(String valorAluguel) {
        this.valorAluguel.setText(valorAluguel);
    }

    public void setDiaPagamento(LocalDate diaPagamento) {
        this.diaPagamento.setText(diaPagamento.toString());
    }

    public void setInquilino(String inquilino) {
        this.inquilino.setText(inquilino);
    }

    public void setImovel(String imovel) {
        this.imovel.setText(imovel);
    }

    public void setProprietario(String proprietario) {
        this.proprietario.setText(proprietario);
    }

    @FXML
    private void delete() throws ClassNotFoundException, SQLException, IOException {
      int cod = Integer.parseInt(this.codigo.getText());
      boolean result = new ContratoAluguelDao().delete(cod);
      if (result) {
        showAlert(AlertType.INFORMATION, "Sucesso", "Contrato deletado com sucesso!");
        ProprietarioSingleton.getInstance().getProprietario().removeContrato(cod);
        Navigation.goToDashboard((Stage) codigo.getScene().getWindow());
      } else {
        showAlert(AlertType.ERROR, "Erro", "Erro ao deletar contrato");
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

