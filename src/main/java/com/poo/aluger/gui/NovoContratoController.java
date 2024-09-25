package com.poo.aluger.gui;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.poo.aluger.dao.ContratoAluguelDao;
import com.poo.aluger.model.ContratoAluguel;
import com.poo.aluger.model.Imovel;
import com.poo.aluger.model.Inquilino;
import com.poo.aluger.model.Proprietario;
import com.poo.aluger.util.Navigation;
import com.poo.aluger.util.ProprietarioSingleton;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class NovoContratoController {

  @FXML
  private DatePicker dataInicio, dataTermino, diaPagamento;

  @FXML
  private TextField valorAluguel;

  @FXML
  private ComboBox<Integer> codInquilino, codImovel;

  @FXML
  private Button okButton;

  @FXML
  public void initialize() {
    Proprietario proprietario = ProprietarioSingleton.getInstance().getProprietario();

    if (proprietario != null) {
      codImovel.setItems(FXCollections.observableArrayList(
          proprietario.getImoveis().stream().map(Imovel::getCodigo).collect(Collectors.toList())));
      codInquilino.setItems(FXCollections.observableArrayList(
          proprietario.getInquilinos().stream().map(Inquilino::getCodigo).collect(Collectors.toList())));
    } else {
      System.err.println("Proprietario is null");
    }
  }

  @FXML
  private void registrar() {
    try {
      LocalDate inicio = dataInicio.getValue();
      LocalDate termino = dataTermino.getValue();
      double valorAluguel = Double.parseDouble(this.valorAluguel.getText());
      LocalDate pagamento = diaPagamento.getValue();
      Integer codInquilinoValue = codInquilino.getValue();
      Integer codImovelValue = codImovel.getValue();
      Proprietario prop = ProprietarioSingleton.getInstance().getProprietario();

      if (codInquilinoValue == null || !inquilinoExists(prop, codInquilinoValue)) {
        showAlert(Alert.AlertType.ERROR, "Error", "Inquilino não encontrado.");
        return;
      }
      if (codImovel == null || !imovelExists(prop, codImovelValue)) {
        showAlert(Alert.AlertType.ERROR, "Error", "Imóvel não encontrado.");
        return;
      }

      Inquilino inquilino = prop.getInquilinos().stream().filter(i -> i.getCodigo() == codInquilinoValue).findFirst()
          .orElse(null);
      Imovel imovel = prop.getImoveis().stream().filter(i -> i.getCodigo() == codImovelValue).findFirst().orElse(null);

      if (inquilino == null || imovel == null) {
        showAlert(Alert.AlertType.ERROR, "Error", "Inquilino ou imóvel não encontrado.");
        return;
      }

      ContratoAluguel contratoAluguel = new ContratoAluguel(inicio, termino, valorAluguel, pagamento, inquilino, imovel, prop);

      int result = new ContratoAluguelDao().insert(contratoAluguel);
      if (result == -1) {
        throw new Exception("Erro ao registrar contrato de aluguel");
      }

      prop.addContrato(contratoAluguel);
      showAlert(Alert.AlertType.INFORMATION, "Success", "Contrato de aluguel registrado com sucesso!");
      goback();
    } catch (NumberFormatException e) {
      showAlert(Alert.AlertType.ERROR, "Error", "Por favor, insira valores válidos para os campos numéricos.");
    } catch (Exception e) {
      showAlert(Alert.AlertType.ERROR, "Error", "Ocorreu um erro ao registrar o contrato de aluguel.");
    }
  }

  private boolean inquilinoExists(Proprietario prop, int codInquilino) {
    return prop.getInquilinos().stream().anyMatch(i -> i.getCodigo() == codInquilino);
  }

  private boolean imovelExists(Proprietario prop, int codImovel) {
    return prop.getImoveis().stream().anyMatch(i -> i.getCodigo() == codImovel);
  }

  private void showAlert(Alert.AlertType alertType, String title, String content) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }

  @FXML
  private void logout() {
    System.out.println("logout");
  }

  @FXML
  private void goback() {
    Navigation.goToDashboard((Stage) valorAluguel.getScene().getWindow());
  }
}
