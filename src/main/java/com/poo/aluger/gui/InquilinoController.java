package com.poo.aluger.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
}
