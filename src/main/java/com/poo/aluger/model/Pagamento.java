package com.poo.aluger.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Pagamento implements Serializable {
  private int codigo;
  private double valor;
  private LocalDate dataPagamento;
  private String formaPagamento;
  private ContratoAluguel contratoAluguel;

  public Pagamento(int codigo, double valor, LocalDate dataPagamento, String formaPagamento,
      ContratoAluguel contratoAluguel) {
    this.codigo = codigo;
    this.valor = valor;
    this.dataPagamento = dataPagamento;
    this.formaPagamento = formaPagamento;
    this.contratoAluguel = contratoAluguel;
  }

  public Pagamento(double valor, LocalDate dataPagamento, String formaPagamento, ContratoAluguel contratoAluguel) {
    this.valor = valor;
    this.dataPagamento = dataPagamento;
    this.formaPagamento = formaPagamento;
    this.contratoAluguel = contratoAluguel;
  }

  public int getCodigo() {
    return codigo;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public LocalDate getDataPagamento() {
    return dataPagamento;
  }

  public void setdataPagamento(LocalDate dataPagamento) {
    this.dataPagamento = dataPagamento;
  }

  public String getFormaPagamento() {
    return formaPagamento;
  }

  public void setFormaPagamento(String formaPagamento) {
    this.formaPagamento = formaPagamento;
  }

  public ContratoAluguel getContratoAluguel() {
    return contratoAluguel;
  }

  public void setContratoAluguel(ContratoAluguel contratoAluguel) {
    this.contratoAluguel = contratoAluguel;
  }

  @Override
  public String toString() {
    return "Pagamento{" +
        "codigo=" + codigo +
        ", valor=" + valor +
        ", dataPagamento='" + dataPagamento + '\'' +
        ", formaPagamento='" + formaPagamento + '\'' +
        ", contratoAluguel=" + contratoAluguel +
        '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, valor, dataPagamento, formaPagamento, contratoAluguel);
  }
}
