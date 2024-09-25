package com.poo.aluger.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ContratoAluguel implements Serializable {
  private int codigo;
  private LocalDate dataInicio;
  private LocalDate dataTermino;
  private double valorAluguel;
  private LocalDate diaPagamento;
  private Inquilino inquilino;
  private Imovel imovel;
  private Proprietario proprietario;

  public ContratoAluguel(int codigo, LocalDate dataInicio, LocalDate dataTermino, double valorAluguel,
      LocalDate diaPagamento, Inquilino inquilino, Imovel imovel, Proprietario proprietario) {
    this.codigo = codigo;
    this.dataInicio = dataInicio;
    this.dataTermino = dataTermino;
    this.valorAluguel = valorAluguel;
    this.diaPagamento = diaPagamento;
    this.inquilino = inquilino;
    this.imovel = imovel;
    this.proprietario = proprietario;
  }

  public ContratoAluguel(LocalDate dataInicio, LocalDate dataTermino, double valorAluguel, LocalDate diaPagamento,
      Inquilino inquilino, Imovel imovel, Proprietario proprietario) {
    this.dataInicio = dataInicio;
    this.dataTermino = dataTermino;
    this.valorAluguel = valorAluguel;
    this.diaPagamento = diaPagamento;
    this.inquilino = inquilino;
    this.imovel = imovel;
    this.proprietario = proprietario;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public int getCodigo() {
    return codigo;
  }

  public LocalDate getDataInicio() {
    return dataInicio;
  }

  public void setDataInicio(LocalDate dataInicio) {
    this.dataInicio = dataInicio;
  }

  public LocalDate getDataTermino() {
    return dataTermino;
  }

  public void setDataTermino(LocalDate dataTermino) {
    this.dataTermino = dataTermino;
  }

  public double getValorAluguel() {
    return valorAluguel;
  }

  public void setValorAluguel(double valorAluguel) {
    this.valorAluguel = valorAluguel;
  }

  public LocalDate getDiaPagamento() {
    return diaPagamento;
  }

  public void setDiaPagamento(LocalDate diaPagamento) {
    this.diaPagamento = diaPagamento;
  }

  public Inquilino getInquilino() {
    return inquilino;
  }

  public void setInquilino(Inquilino inquilino) {
    this.inquilino = inquilino;
  }

  public Imovel getImovel() {
    return imovel;
  }

  public void setImovel(Imovel imovel) {
    this.imovel = imovel;
  }

  public Proprietario getProprietario() {
    return proprietario;
  }

  public void setProprietario(Proprietario proprietario) {
    this.proprietario = proprietario;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    ContratoAluguel that = (ContratoAluguel) o;
    return codigo == that.codigo;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(codigo);
  }

  @Override
  public String toString() {
    return "ContratoAluguel{" +
        "codigo=" + codigo +
        ", dataInicio=" + dataInicio +
        ", dataTermino=" + dataTermino +
        ", valorAluguel=" + valorAluguel +
        ", diaPagamento=" + diaPagamento +
        ", inquilino=" + inquilino +
        ", imovel=" + imovel +
        ", proprietario=" + proprietario +
        '}';
  }
}
