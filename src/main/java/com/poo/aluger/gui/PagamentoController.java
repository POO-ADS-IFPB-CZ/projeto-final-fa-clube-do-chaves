package com.poo.aluger.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PagamentoController {

  @FXML
  private Label valor, data, forma, codigoContrato, codigo;

  public void setCodigo(String codigo) {
    this.codigo.setText(codigo);
  }

  public void setValor(String valor) {
    this.valor.setText(valor);
  }

  public void setData(String data) {
    this.data.setText(data);
  }

  public void setForma(String forma) {
    this.forma.setText(forma);
  }

  public void setCodigoContrato(String codigoContrato) {
    this.codigoContrato.setText(codigoContrato);
  }

}
