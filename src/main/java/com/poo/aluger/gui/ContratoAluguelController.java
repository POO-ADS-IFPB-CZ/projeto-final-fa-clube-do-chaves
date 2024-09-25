package com.poo.aluger.gui;

import java.time.LocalDate;

import com.poo.aluger.dao.ContratoAluguelDao;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

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

    // Ação para deletar o contrato
    @FXML
    private void delete() {
      int cod = Integer.parseInt(this.codigo.getText());
      boolean result = new ContratoAluguelDao().delete(cod);
      if (result) {
        showAlert(AlertType.INFORMATION, "Sucesso", "Contrato deletado com sucesso!");
        ProprietarioSingleton.getInstance().getProprietario().removeContrato(cod);
        Navigation.goToDashboard((Stage) codigo.getScene().getWindow());
    } else {
        showAlert(AlertType.ERROR, "Erro", "Erro ao deletar contrato");
    }
        
        if (contratoAluguel != null) {
            // Aqui você pode chamar a lógica de exclusão do contrato
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Contrato Excluído");
            alert.setHeaderText(null);
            alert.setContentText("O contrato de aluguel foi deletado com sucesso.");
            alert.showAndWait();

            // Lógica adicional para remover o contrato da lista ou banco de dados pode ser inserida aqui
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Nenhum contrato foi selecionado para deletar.");
            alert.showAndWait();
        }
    }
}

