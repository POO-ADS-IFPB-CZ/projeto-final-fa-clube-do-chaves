package com.poo.aluger.gui;

import java.io.IOException;

import com.poo.aluger.model.ContratoAluguel;
import com.poo.aluger.model.Imovel;
import com.poo.aluger.model.Inquilino;
import com.poo.aluger.model.Manutencao;
import com.poo.aluger.model.Pagamento;
import com.poo.aluger.model.Proprietario;
import com.poo.aluger.util.Navigation;
import com.poo.aluger.util.ProprietarioSingleton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DashboardController {

  private Parent root;
  private Scene scene;
  private Stage stage;
  private FXMLLoader fxmlLoader;

  @FXML
  private Text imoveisQtd, inquilinosQtd, manutencoesQtd, pagamentosQtd, contratosQtd, saldoVal, nomeProp;

  public void initialize() {
    Proprietario proprietario = ProprietarioSingleton.getInstance().getProprietario();

    if (proprietario != null) {
      imoveisQtd.setText(String.valueOf(proprietario.getQtdImoveis()));
      inquilinosQtd.setText(String.valueOf(proprietario.getQtdInquilinos()));
      manutencoesQtd.setText(String.valueOf(proprietario.getQtdManutencoes()));
      pagamentosQtd.setText(String.valueOf(proprietario.getQtdPagamentos()));
      contratosQtd.setText(String.valueOf(proprietario.getQtdContratos()));
      saldoVal.setText(String.valueOf(proprietario.getSaldo()));
      nomeProp.setText(proprietario.getNome());
    } else {
      System.err.println("Proprietario is null");
    }
  }

  @FXML
  public void imoveis(ActionEvent event) throws IOException {
    fxmlLoader = new FXMLLoader(DashboardController.class.getResource("houses.fxml"));
    root = fxmlLoader.load();

    GenericController controller = fxmlLoader.getController();
    controller.initialize(Imovel.class);

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setTitle("Houses");
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void inquilinos(ActionEvent event) throws IOException {
    fxmlLoader = new FXMLLoader(DashboardController.class.getResource("houses.fxml"));
    root = fxmlLoader.load();

    GenericController controller = fxmlLoader.getController();
    controller.initialize(Inquilino.class);

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setTitle("Inquilinos");
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void manutencoes(ActionEvent event) throws IOException {
    fxmlLoader = new FXMLLoader(DashboardController.class.getResource("houses.fxml"));
    root = fxmlLoader.load();

    GenericController controller = fxmlLoader.getController();
    controller.initialize(Manutencao.class);

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setTitle("Manutenções");
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void pagamentos(ActionEvent event) throws IOException {
    fxmlLoader = new FXMLLoader(DashboardController.class.getResource("houses.fxml"));
    root = fxmlLoader.load();

    GenericController controller = fxmlLoader.getController();
    controller.initialize(Pagamento.class);

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setTitle("Pagamentos");
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void contratos(ActionEvent event) throws IOException {
    fxmlLoader = new FXMLLoader(DashboardController.class.getResource("houses.fxml"));
    root = fxmlLoader.load();

    GenericController controller = fxmlLoader.getController();
    controller.initialize(ContratoAluguel.class);

    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setTitle("Pagamentos");
    stage.setScene(scene);
    stage.show();
  }   

  @FXML
  public void logout(ActionEvent event) throws IOException {
    Navigation.logout((Stage) ((Node) event.getSource()).getScene().getWindow());
  }

  @FXML
  public void adicionarImovel(ActionEvent event) throws IOException {
    fxmlLoader = new FXMLLoader(DashboardController.class.getResource("novoImovel.fxml"));
    root = fxmlLoader.load();
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setTitle("Novo Imóvel");
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void adicionarContrato(ActionEvent event) throws IOException {
    fxmlLoader = new FXMLLoader(DashboardController.class.getResource("FormContratoAluguel.fxml"));
    root = fxmlLoader.load();
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setTitle("Novo Contrato de Aluguel");
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void adicionarPagamento(ActionEvent event) throws IOException {
    fxmlLoader = new FXMLLoader(DashboardController.class.getResource("FormPagamento.fxml"));
    root = fxmlLoader.load();
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setTitle("Novo Pagamento");
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void adicionarManutencao(ActionEvent event) throws IOException {
    fxmlLoader = new FXMLLoader(DashboardController.class.getResource("FormManutencao.fxml"));
    root = fxmlLoader.load();
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setTitle("Nova Manutenção");
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void adicionarInquilino(ActionEvent event) throws IOException {
    fxmlLoader = new FXMLLoader(DashboardController.class.getResource("FormInquilino.fxml"));
    root = fxmlLoader.load();
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setTitle("Novo Inquilino");
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void conta(ActionEvent event) throws  IOException {
    fxmlLoader = new FXMLLoader(DashboardController.class.getResource("Conta.fxml"));
    root = fxmlLoader.load();
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setTitle("Conta");
    stage.setScene(scene);
    stage.show();
  }
}
