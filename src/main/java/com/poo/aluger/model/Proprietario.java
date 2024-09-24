package com.poo.aluger.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.poo.aluger.dao.ContratoAluguelDao;
import com.poo.aluger.dao.ImovelDao;
import com.poo.aluger.dao.InquilinoDao;
import com.poo.aluger.dao.ManutencaoDao;
import com.poo.aluger.dao.PagamentoDao;

public class Proprietario implements Serializable {
  private Integer codigo;
  private String nome;
  private String email;
  private String senha;
  private double saldo;
  private int qtdContratos;
  private int qtdImoveis;
  private int qtdManutencoes;
  private int qtdPagamentos;
  private int qtdInquilinos;
  private List<Imovel> imoveis;
  private List<ContratoAluguel> contratos;
  private List<Manutencao> manutencoes;
  private List<Pagamento> pagamentos;
  private List<Inquilino> inquilinos;

  public Proprietario(int codigo, String nome, String email, String senha) {
    this.codigo = codigo;
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    saldo = 0;
    qtdContratos = 0;
    qtdImoveis = 0;
    qtdManutencoes = 0;
    qtdPagamentos = 0;
    qtdInquilinos = 0;
  }

  public Proprietario(String nome, String email, String senha) {
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    saldo = 0;
    qtdContratos = 0;
    qtdImoveis = 0;
    qtdManutencoes = 0;
    qtdPagamentos = 0;
    qtdInquilinos = 0;
  }

  public Proprietario(Integer codigo, String nome, String email, String senha, double saldo, int qtdContratos,
      int qtdImoveis, int qtdManutencoes, int qtdPagamentos, int qtdInquilinos) {
    this.codigo = codigo;
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    this.saldo = saldo;
    this.qtdContratos = qtdContratos;
    this.qtdImoveis = qtdImoveis;
    this.qtdManutencoes = qtdManutencoes;
    this.qtdPagamentos = qtdPagamentos;
    this.qtdInquilinos = qtdInquilinos;
    imoveis = new ArrayList<>();
    contratos = new ArrayList<>();
    manutencoes = new ArrayList<>();
    pagamentos = new ArrayList<>();
    inquilinos = new ArrayList<>();
    saldo = 0.0;
    qtdContratos = 0;
    qtdImoveis = 0;
    qtdManutencoes = 0;
    qtdPagamentos = 0;
    qtdInquilinos = 0;
  }

  public Proprietario(String nome, String email, String senha, double saldo, int qtdContratos, int qtdImoveis,
                      int qtdManutencoes, int qtdPagamentos, int qtdInquilinos) {
    this(null, nome, email, senha, saldo, qtdContratos, qtdImoveis, qtdManutencoes, qtdPagamentos, qtdInquilinos);
  }

  public int getCodigo() {
    return codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  public int getQtdContratos() {
    return qtdContratos;
  }

  public void setQtdContratos(int qtdContratos) {
    this.qtdContratos = qtdContratos;
  }

  public int getQtdImoveis() {
    return qtdImoveis;
  }

  public void setQtdImoveis(int qtdImoveis) {
    this.qtdImoveis = qtdImoveis;
  }

  public int getQtdManutencoes() {
    return qtdManutencoes;
  }

  public void setQtdManutencoes(int qtdManutencoes) {
    this.qtdManutencoes = qtdManutencoes;
  }

  public int getQtdPagamentos() {
    return qtdPagamentos;
  }

  public void setQtdPagamentos(int qtdPagamentos) {
    this.qtdPagamentos = qtdPagamentos;
  }

  public int getQtdInquilinos() {
    return qtdInquilinos;
  }

  public void setQtdInquilinos(int qtdInquilinos) {
    this.qtdInquilinos = qtdInquilinos;
  }

  public void loadInfo() throws ClassNotFoundException, SQLException, IOException {
      imoveis = new ImovelDao().findAll(codigo);
      contratos = new ContratoAluguelDao().findAll(codigo);
      manutencoes = new ManutencaoDao().findAll(codigo);
      pagamentos = new PagamentoDao().findAll(codigo);
      inquilinos = new InquilinoDao().findAll(codigo);
    saldo = pagamentos.stream().mapToDouble(Pagamento::getValor).sum()
        - manutencoes.stream().mapToDouble(Manutencao::getCusto).sum();
    qtdContratos = contratos.size();
    qtdImoveis = imoveis.size();
    qtdManutencoes = manutencoes.size();
    qtdPagamentos = pagamentos.size();
    qtdInquilinos = inquilinos.size();
  }

  @Override
  public String toString() {
    return "Proprietario{" +
        "codigo=" + codigo +
        ", nome='" + nome + '\'' +
        ", email='" + email + '\'' +
        ", senha='" + senha + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Proprietario that = (Proprietario) o;
    return codigo == that.codigo && Objects.equals(nome, that.nome)
        && Objects.equals(email, that.email) && Objects.equals(senha, that.senha);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, nome, email, senha);
  }
}
