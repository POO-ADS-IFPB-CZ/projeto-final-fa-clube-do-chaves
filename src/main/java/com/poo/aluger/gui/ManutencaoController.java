package com.poo.aluger.gui;

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

  public void setDataInicio(String dataInicio) {
    this.dataInicio.setText(dataInicio);
  }

  public void setDataTermino(String dataTermino) {
    this.dataTermino.setText(dataTermino);
  }

  public void setValor(String valor) {
    this.valor.setText(valor);
  }

  public void setCodigoImovel(String codigoImovel) {
    this.codigoImovel.setText(codigoImovel);
  }
}
