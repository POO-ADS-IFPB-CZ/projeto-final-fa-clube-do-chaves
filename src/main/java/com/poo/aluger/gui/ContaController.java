package com.poo.aluger.gui;

import com.poo.aluger.model.Proprietario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ContaController {

    @FXML
    private Text nomeLabel;  // Mudança de Label para Text

    @FXML
    private Text emailLabel;  // Mudança de Label para Text

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField textField;

    @FXML
    private Button toggleButton;

    private Proprietario proprietario;

    // Método para inicializar o objeto Proprietario e atualizar a interface
    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
        nomeLabel.setText(proprietario.getNome());  // Usando o Text
        emailLabel.setText(proprietario.getEmail());  // Usando o Text
        passwordField.setText(proprietario.getSenha());
    }

    @FXML
    private void togglePasswordVisibility() {
        if (textField.isVisible()) {
            // Mostrar a senha como campo de senha
            textField.setVisible(false);
            textField.setManaged(false);
            passwordField.setVisible(true);
            passwordField.setManaged(true);
            toggleButton.setText("Mostrar");
        } else {
            // Mostrar a senha como texto simples
            textField.setText(passwordField.getText());
            textField.setVisible(true);
            textField.setManaged(true);
            passwordField.setVisible(false);
            passwordField.setManaged(false);
            toggleButton.setText("Ocultar");
        }
    }
}
