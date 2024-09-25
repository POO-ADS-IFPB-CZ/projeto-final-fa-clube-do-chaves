package com.poo.aluger.gui;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.poo.aluger.dao.PagamentoDao;
import com.poo.aluger.model.ContratoAluguel;
import com.poo.aluger.model.Pagamento;
import com.poo.aluger.model.Proprietario;
import com.poo.aluger.util.Navigation;
import com.poo.aluger.util.ProprietarioSingleton;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class PagamentoController {

  @FXML
  private TextField valorPagamentoField;

  @FXML
  private DatePicker dataPagamentoPicker;

  @FXML
  private ToggleGroup formaPagamentoGroup;

  @FXML
  private ComboBox<Integer> codContratoAluguelComboBox;

  @FXML
  public void initialize() {
    Proprietario proprietario = ProprietarioSingleton.getInstance().getProprietario();

    if (proprietario != null) {
      codContratoAluguelComboBox.setItems(FXCollections.observableArrayList(
          proprietario.getContratos().stream().map(ContratoAluguel::getCodigo).collect(Collectors.toList())));
    } else {
      System.err.println("Proprietario is null");
    }
  }

  @FXML
  private void registrar() {
    try {
      double valor = Double.parseDouble(valorPagamentoField.getText());
      LocalDate data = dataPagamentoPicker.getValue();
      RadioButton selectedRadioButton = (RadioButton) formaPagamentoGroup.getSelectedToggle();
      String formaPagamento = selectedRadioButton != null ? selectedRadioButton.getText() : null;
      Integer codContratoAluguel = codContratoAluguelComboBox.getValue();

      ContratoAluguel contratoAluguel = ProprietarioSingleton.getInstance().getProprietario().getContratos().stream()
          .filter(c -> c.getCodigo() == codContratoAluguel).findFirst().orElse(null);
      if (codContratoAluguel == null) {
        throw new Exception("Erro ao registrar pagamento");
      }
      Pagamento pagamento = new Pagamento(valor, data, formaPagamento, contratoAluguel);

      int result = new PagamentoDao().insert(pagamento);
      if (result == -1) {
        throw new Exception("Erro ao registrar pagamento");
      }

      ProprietarioSingleton.getInstance().getProprietario().addPagamento(pagamento);
      showAlert(Alert.AlertType.INFORMATION, "Success", "Pagamento registrado com sucesso!");
      goback();
    } catch (NumberFormatException e) {
      showAlert(Alert.AlertType.ERROR, "Error", "Por favor, insira valores válidos para os campos numéricos.");
    } catch (Exception e) {
      showAlert(Alert.AlertType.ERROR, "Error", "Ocorreu um erro ao registrar o pagamento.");
    }
  }

  @FXML
  private void goback() {
    Navigation.goToDashboard((Stage) valorPagamentoField.getScene().getWindow());
  }

  @FXML
  private void logout() {
    Navigation.goToDashboard((Stage) valorPagamentoField.getScene().getWindow());
  }

  private void showAlert(Alert.AlertType alertType, String title, String content) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }
}
