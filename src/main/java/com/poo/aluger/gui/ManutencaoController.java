package com.poo.aluger.gui;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
}
