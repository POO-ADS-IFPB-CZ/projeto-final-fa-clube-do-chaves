package com.poo.aluger.gui;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import com.poo.aluger.model.ContratoAluguel;
import com.poo.aluger.model.Imovel;
import com.poo.aluger.model.Inquilino;
import com.poo.aluger.model.Manutencao;
import com.poo.aluger.model.Pagamento;
import com.poo.aluger.util.ProprietarioSingleton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GenericController {

  @FXML
  private VBox generiContainer; // VBox where items will be added dynamically

  @SuppressWarnings("unchecked")
  public <T> void initialize(Class<T> type) throws IOException {
    List<T> items;
    if (type == Imovel.class) {
      items = (List<T>) ProprietarioSingleton.getInstance().getProprietario().getImoveis();
    } else if (type == ContratoAluguel.class) {
      items = (List<T>) ProprietarioSingleton.getInstance().getProprietario().getContratos();
    } else if (type == Manutencao.class) {
      items = (List<T>) ProprietarioSingleton.getInstance().getProprietario().getManutencoes();
    } else if (type == Pagamento.class) {
      items = (List<T>) ProprietarioSingleton.getInstance().getProprietario().getPagamentos();
    } else if (type == Inquilino.class) {
      items = (List<T>) ProprietarioSingleton.getInstance().getProprietario().getInquilinos();
    } else {
      throw new IllegalArgumentException("Unsupported type: " + type);
    }
    addMultipleItems(items);
  }

  public <T> void addMultipleItems(List<T> items) throws IOException {
    for (T item : items) {
      if (item instanceof Imovel) {
        addImovel((Imovel) item, "imovel.fxml");
      } else if (item instanceof Inquilino) {
        addInquilino((Inquilino) item, "inquilino.fxml");
      } else if (item instanceof Pagamento) {
        addPagamento((Pagamento) item, "contratoAluguel.fxml");
      } else if (item instanceof Manutencao) {
        addManutencao((Manutencao) item, "manutencao.fxml");
      } else if (item instanceof ContratoAluguel) {
         addContratoAluguel((ContratoAluguel) item, "contratoAluguel.fxml");
      } else {
        throw new IllegalArgumentException("Item is not of type Imovel: " + item);
      }
    }

  }

  public void addImovel(Imovel imovel, String resource) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(GenericController.class.getResource("imovel.fxml"));
    Node imovelNode = fxmlLoader.load();

    ImovelController imovelController = fxmlLoader.getController();
    imovelController.setTipo(imovel.getTipo());
    imovelController.setImage(imovel.getFoto());
    imovelController.setEstado(imovel.getEstado());
    imovelController.setCidade(imovel.getCidade());
    imovelController.setBairro(imovel.getBairro());
    imovelController.setRua(imovel.getRua());
    imovelController.setNumero(String.valueOf(imovel.getNumero()));
    imovelController.setCodigo(String.valueOf(imovel.getCodigo()));
    imovelController.setAreaTotal(String.valueOf(imovel.getAreaTotal()));
    imovelController.setQuantidadeQuartos(String.valueOf(imovel.getQuantidadeQuartos()));
    imovelController.setQuantidadeBanheiros(String.valueOf(imovel.getQuantidadeBanheiros()));
    imovelController.setStatus(imovel.getStatus());
    imovelController.setDescricao(imovel.getDescricao());
    generiContainer.getChildren().add(imovelNode);
  }

  public void addInquilino(Inquilino inquilino, String resource) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(GenericController.class.getResource("inquilino.fxml"));
    Node inquilinoNode = fxmlLoader.load();

    InquilinoController inquilinoController = fxmlLoader.getController();

    inquilinoController.setCodigo(String.valueOf(inquilino.getCodigo()));
    inquilinoController.setNome(inquilino.getNome());
    inquilinoController.setCpf(inquilino.getCpf());
    inquilinoController.setTelefone1(inquilino.getTelefone1());
    inquilinoController.setTelefone2(inquilino.getTelefone2());

    generiContainer.getChildren().add(inquilinoNode);
  }

  public void addPagamento(Pagamento pagamento, String resource) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(GenericController.class.getResource("pagamento.fxml"));
    Node pagamentoNode = fxmlLoader.load();

    PagamentoController pagamentoController = fxmlLoader.getController();

    pagamentoController.setPaymentDetails(
        String.valueOf(pagamento.getValor()),
        pagamento.getFormaPagamento(),
        pagamento.getDataPagamento().toString(),
        String.valueOf(pagamento.getContratoAluguel().getCodigo()),
        String.valueOf(pagamento.getCodigo()));

    generiContainer.getChildren().add(pagamentoNode);
  }

  public void addManutencao(Manutencao manutencao, String resource) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(GenericController.class.getResource("manutencao.fxml"));
    Node manutencaoNode = fxmlLoader.load();

    ManutencaoController manutencaoController = fxmlLoader.getController();
    manutencaoController.setTipo(manutencao.getTipo());
    manutencaoController.setValor(manutencao.getCusto() + "");
    manutencaoController.setCodigo(manutencao.getCodigo() + "");
    manutencaoController.setDataInicio(manutencao.getDataInicio());
    manutencaoController.setDataTermino(manutencao.getDataTermino());
    manutencaoController.setDataInicio(manutencao.getDataInicio());
    manutencaoController.setCodigoImovel(manutencao.getImovel().getCodig() + "");

    generiContainer.getChildren().add(manutencaoNode);
  }

  public void addContratoAluguel(ContratoAluguel contratoAluguel, String resource) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(GenericController.class.getResource("contratoAluguel.fxml"));
    Node contratoAluguelNode = fxmlLoader.load();

    ContratoAluguelController contratoAluguelController = fxmlLoader.getController();
    contratoAluguelController.setCodigo(contratoAluguel.getCodigo() + "");
    contratoAluguelController.setDataInicio(contratoAluguel.getDataInicio());
    contratoAluguelController.setDataTermino(contratoAluguel.getDataTermino());
    contratoAluguelController.setValorAluguel(contratoAluguel.getValorAluguel() + "");
    contratoAluguelController.setDiaPagamento(contratoAluguel.getDiaPagamento());
    contratoAluguelController.setInquilino(contratoAluguel.getInquilino().getCodigo() + "");
    contratoAluguelController.setImovel(contratoAluguel.getImovel().getCodig() + "");
    contratoAluguelController.setProprietario(contratoAluguel.getProprietario().getCodigo() + "");

    generiContainer.getChildren().add(contratoAluguelNode);
  }

  @FXML
  public void up(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("All Files", "*.*"));
    File file = fileChooser.showOpenDialog(null);
    if (file != null) {
      System.out.println("File chosen: " + file.getName());
    } else {
      System.out.println("File selection cancelled.");
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

  @FXML
  public void addImovel(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("novoImovel.fxml")));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setTitle("Adicionar Imóvel");
    stage.setScene(scene);
    stage.show();
  }

}
