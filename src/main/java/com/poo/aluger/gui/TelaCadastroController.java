package org.example.telacadastro;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TelaCadastroController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}