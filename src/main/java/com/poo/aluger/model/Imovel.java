package com.poo.aluger.model;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Imovel implements Serializable {
  private int codigo;
  private BufferedImage foto;
  private String tipo;
  private double areaTotal;
  private int quantidadeQuartos;
  private String status;
  private int quantidadeBanheiros;
  private String descricao;
  private double valorAluguel;
  private Proprietario proprietario;
  // Endereço
  private String rua;
  private int numero;
  private String bairro;
  private String cidade;
  private String estado;

  public Imovel(int codigo, BufferedImage foto, String rua, int numero, String bairro, String cidade,
      String estado, String tipoImovel, double areaTotal, int qtdQuartos, String status,
      int qtdBanheiros, String descricao, Proprietario proprietario2) {
    this.foto = foto;
    this.tipo = tipoImovel;
    this.areaTotal = areaTotal;
    this.quantidadeQuartos = qtdQuartos;
    this.status = status;
    this.quantidadeBanheiros = qtdBanheiros;
    this.descricao = descricao;
    this.proprietario = proprietario2;
    this.rua = rua;
    this.numero = numero;
    this.bairro = bairro;
    this.cidade = cidade;
    this.estado = estado;
  }

  public Imovel(BufferedImage foto, String rua, int numero, String bairro, String cidade,
      String estado, String tipoImovel, double areaTotal, int qtdQuartos, String status,
      int qtdBanheiros, String descricao, Proprietario proprietario) {
    this.foto = foto;
    this.tipo = tipoImovel;
    this.areaTotal = areaTotal;
    this.quantidadeQuartos = qtdQuartos;
    this.status = status;
    this.quantidadeBanheiros = qtdBanheiros;
    this.descricao = descricao;
    this.proprietario = proprietario;
    this.rua = rua;
    this.numero = numero;
    this.bairro = bairro;
    this.cidade = cidade;
    this.estado = estado;
  }

  public int getCodigo() {
    return codigo;
  }

  public BufferedImage getFoto() {
    return foto;
  }

  public void setFoto(BufferedImage foto) {
    this.foto = foto;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public double getAreaTotal() {
    return areaTotal;
  }

  public void setAreaTotal(double areaTotal) {
    this.areaTotal = areaTotal;
  }

  public int getQuantidadeQuartos() {
    return quantidadeQuartos;
  }

  public void setQuantidadeQuartos(int quantidadeQuartos) {
    this.quantidadeQuartos = quantidadeQuartos;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getQuantidadeBanheiros() {
    return quantidadeBanheiros;
  }

  public void setQuantidadeBanheiros(int quantidadeBanheiros) {
    this.quantidadeBanheiros = quantidadeBanheiros;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public double getValorAluguel() {
    return valorAluguel;
  }

  public void setValorAluguel(float valorAluguel) {
    this.valorAluguel = valorAluguel;
  }

  public Proprietario getProprietario() {
    return proprietario;
  }

  public void setProprietario(Proprietario proprietario) {
    this.proprietario = proprietario;
  }

  public String getRua() {
    return rua;
  }

  public void setRua(String rua) {
    this.rua = rua;
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  @Override
  public String toString() {
    return "Imovel{" +
        "codigo=" + codigo +
        ", tipo='" + tipo + '\'' +
        ", areaTotal=" + areaTotal +
        ", quantidadeQuartos=" + quantidadeQuartos +
        ", status='" + status + '\'' +
        ", quantidadeBanheiros=" + quantidadeBanheiros +
        ", descricao='" + descricao + '\'' +
        ", valorAluguel=" + valorAluguel +
        ", proprietario=" + proprietario +
        ", rua='" + rua + '\'' +
        ", numero=" + numero +
        ", bairro='" + bairro + '\'' +
        ", cidade='" + cidade + '\'' +
        ", estado='" + estado + '\'' +
        '}';
  }
}
