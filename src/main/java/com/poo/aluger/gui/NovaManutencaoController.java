package com.poo.aluger.gui;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.poo.aluger.model.Imovel;
import com.poo.aluger.model.Proprietario;
import com.poo.aluger.util.Navigation;

import com.poo.aluger.util.ProprietarioSingleton;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class NovaManutencaoController {

    @FXML
    private TextField tipo;

    @FXML
    private DatePicker dataInicio;

    @FXML
    private DatePicker dataTermino;

    @FXML
    private TextField custo;

    @FXML
    private Button OkButton;

    @FXML
    private ComboBox<Integer> codImovelComboBox;

    @FXML
    public void initialize() {
        Proprietario proprietario = ProprietarioSingleton.getInstance().getProprietario();

        if (proprietario != null) {
            List<Integer> codigosImoveis = proprietario.getImoveis().stream()
                    .map(Imovel::getCodigo)
                    .collect(Collectors.toList());
            codImovelComboBox.setItems(FXCollections.observableArrayList(codigosImoveis));
        } else {
            System.err.println("Proprietario is null");
        }
    }

    @FXML
    private void registrar() {
        try {
            String tipoManutencao = tipo.getText();
            LocalDate dataInicioManutencao = dataInicio.getValue();
            LocalDate dataTerminoManutencao = dataTermino.getValue();
            double custoManutencao = Double.parseDouble(custo.getText());
            int codigoImovel = codImovelComboBox.getValue();

            // Adicione a lógica para registrar a manutenção
            // Por exemplo, salvar no banco de dados ou chamar um serviço

            showAlert(Alert.AlertType.INFORMATION, "Success", "Manutenção registrada com sucesso!");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Por favor, insira valores válidos para os campos numéricos.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Ocorreu um erro ao registrar a manutenção.");
        }
    }

    @FXML
    private void goback() {
        Navigation.goToDashboard((Stage) tipo.getScene().getWindow());
    }

    @FXML
    private void logout() {
        Navigation.goToDashboard((Stage) tipo.getScene().getWindow());
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}