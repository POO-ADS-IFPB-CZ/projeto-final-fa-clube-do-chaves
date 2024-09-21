package com.poo.aluger.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Imovel implements Serializable {
    private int codigo;
    private byte[] foto;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String tipo;
    private double areaTotal;
    private int qtdQuartos;
    private String status;
    private int qtdBanheiros;
    private String descricao;
    private Proprietario proprietario;

    public Imovel(int codigo, byte[] foto, String rua, int numero, String bairro, String cidade,
                  String estado, String tipo, double areaTotal, int qtdQuartos, String status,
                  int qtdBanheiros, String descricao, Proprietario proprietario) {
        this.codigo = codigo;
        this.foto = foto;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.tipo = tipo;
        this.areaTotal = areaTotal;
        this.qtdQuartos = qtdQuartos;
        this.status = status;
        this.qtdBanheiros = qtdBanheiros;
        this.descricao = descricao;
        this.proprietario = proprietario;
    }

    public Imovel(byte[] foto, String rua, int numero, String bairro, String cidade, String estado, String tipo, double areaTotal, int qtdQuartos, String status, int qtdBanheiros, String descricao, Proprietario proprietario) {
        this.foto = foto;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.tipo = tipo;
        this.areaTotal = areaTotal;
        this.qtdQuartos = qtdQuartos;
        this.status = status;
        this.qtdBanheiros = qtdBanheiros;
        this.descricao = descricao;
        this.proprietario = proprietario;
    }

    public int getCodigo() {
        return codigo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
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

    public int getQtdQuartos() {
        return qtdQuartos;
    }

    public void setQtdQuartos(int qtdQuartos) {
        this.qtdQuartos = qtdQuartos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQtdBanheiros() {
        return qtdBanheiros;
    }

    public void setQtdBanheiros(int qtdBanheiros) {
        this.qtdBanheiros = qtdBanheiros;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imovel imovel = (Imovel) o;
        return codigo == imovel.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    @Override
    public String toString() {
        return "Imovel{" +
                "codigo=" + codigo +
                ", foto=" + Arrays.toString(foto) +
                ", rua='" + rua + '\'' +
                ", numero=" + numero +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", tipo='" + tipo + '\'' +
                ", areaTotal=" + areaTotal +
                ", qtdQuartos=" + qtdQuartos +
                ", status='" + status + '\'' +
                ", qtdBanheiros=" + qtdBanheiros +
                ", descricao='" + descricao + '\'' +
                ", proprietario=" + proprietario +
                '}';
    }
}