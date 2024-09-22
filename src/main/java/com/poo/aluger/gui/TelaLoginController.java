package org.example.telalogin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TelaLoginController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
