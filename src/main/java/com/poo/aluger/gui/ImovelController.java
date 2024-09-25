package com.poo.aluger.gui;

import java.awt.image.BufferedImage;

import com.poo.aluger.dao.ImovelDao;
import com.poo.aluger.util.ImageConverter;
import com.poo.aluger.util.Navigation;
import com.poo.aluger.util.ProprietarioSingleton;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    Image img = imagem == null ? null : ImageConverter.getImage(imagem);
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

  @FXML
  private void delete() {
    try {
      int cod = Integer.parseInt(this.codigo.getText());
      boolean result = new ImovelDao().delete(cod);
      if (result) {
        showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Imóvel deletado com sucesso");
        ProprietarioSingleton.getInstance().getProprietario().removeImovel(cod);
        Navigation.goToDashboard((Stage) image.getScene().getWindow());
      } else {
        showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao deletar imóvel");
      }
    } catch (Exception e) {
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
