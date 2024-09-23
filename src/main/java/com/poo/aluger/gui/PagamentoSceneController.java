package com.poo.aluger.gui;

import java.io.IOException;
import java.util.Objects;

import com.poo.aluger.model.Pagamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PagamentoSceneController {

  @FXML
  private VBox pagamentos;

  public void addPagamento(Pagamento pagamento) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("pagamento.fxml"));
    Node pagamentoNode = loader.load();

    PagamentoController controller = loader.getController();

    controller.setCodigo(String.valueOf(pagamento.getCodigo()));
    controller.setValor(String.valueOf(pagamento.getValor()));
    controller.setData(pagamento.getDataPagamento());
    controller.setForma(pagamento.getFormaPagamento());
    controller.setCodigoContrato(String.valueOf(pagamento.getContratoAluguel().getCodigo()));

    pagamentos.getChildren().add(pagamentoNode);
  }

  public void addPagamentos(Pagamento[] pagamentos) throws IOException {
    for (Pagamento pagamento : pagamentos) {
      addPagamento(pagamento);

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
