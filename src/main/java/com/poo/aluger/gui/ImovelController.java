package com.poo.aluger.gui;

import com.poo.aluger.util.ImageConverter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;

public class ImovelController {

  @FXML
  private Label tipo, estado, cidade, bairro, rua, numero, codigo, areaTotal, quartos,
      banheiros, status, nomeInquilino, codigoInquilino, telefone1, telefone2, cpf;

  @FXML
  private ImageView image;

  public void setTipo(String tipo) {
    this.tipo.setText(tipo);
  }

  public void setImage(BufferedImage imagem) {
    Image img = ImageConverter.getImage(imagem);
    image.setImage(img);
  }

  public void setEstado(String estado) {
    this.estado.setText(estado);
  }

  public void setCidade(String cidade) {
    this.cidade.setText(cidade);
  }

  public void setBairro(String bairro) {
    this.bairro.setText(bairro);
  }

  public void setRua(String rua) {
    this.rua.setText(rua);
  }

  public void setNumero(String numero) {
    this.numero.setText(numero);
  }

  public void setCodigo(String codigo) {
    this.codigo.setText(codigo);
  }

  public void setAreaTotal(String areaTotal) {
    this.areaTotal.setText(areaTotal);
  }

  public void setQuantidadeQuartos(String quartos) {
    this.quartos.setText(quartos);
  }

  public void setQuantidadeBanheiros(String banheiros) {
    this.banheiros.setText(banheiros);
  }

  public void setStatus(String status) {
    this.status.setText(status);
  }

  public void setNomeInquilino(String nomeInquilino) {
    this.nomeInquilino.setText(nomeInquilino);
  }

  public void setCodigoInquilino(String codigoInquilino) {
    this.codigoInquilino.setText(codigoInquilino);
  }

  public void setTelefone1(String telefone1) {
    this.telefone1.setText(telefone1);
  }

  public void setTelefone2(String telefone2) {
    this.telefone2.setText(telefone2);
  }

  public void setCpf(String cpf) {
    this.cpf.setText(cpf);
  }
}
