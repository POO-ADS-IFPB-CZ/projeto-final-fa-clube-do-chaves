package com.poo.aluger.gui;

import java.io.IOException;
import java.util.Objects;

import com.poo.aluger.model.Manutencao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManutencaoSceneController {

  @FXML
  private VBox manutencaoContainer;

  public void addManutencao(Manutencao manutencao) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("manutencao.fxml"));
    Node manutencaoNode = loader.load();

    ManutencaoController controller = loader.getController();
    var custo = manutencao.getCusto();

    controller.setCodigo(String.valueOf(manutencao.getCodigo()));
    controller.setTipo(manutencao.getTipo());
    controller.setDataInicio(manutencao.getDataInicio());
    controller.setDataTermino(manutencao.getDataTermino());
    controller.setValor(String.valueOf(manutencao.getCusto()));
    controller.setCodigoImovel(String.valueOf(manutencao.getImovel().getCodigo()));

    manutencaoContainer.getChildren().add(manutencaoNode);
  }

  public void addManutencoes(Manutencao[] manutencoes) throws IOException {
    for (Manutencao manutencao : manutencoes) {
      addManutencao(manutencao);
    }
  }

  @FXML
  public void goback(ActionEvent event) throws IOException {
    System.out.println("goback");
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setTitle("Dashboard");
    stage.setScene(scene);
    stage.show();
  }
}
