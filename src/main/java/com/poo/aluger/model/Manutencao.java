package com.poo.aluger.model;

import java.io.Serializable;
import java.util.Objects;

public class Manutencao implements Serializable {
    private int codigo;
    private  String tipo;
    private String dataInicio;
    private String dataTermino;
    private double custo;
    private  Imovel imovel;

    public Manutencao(int codigo, String tipo, String dataInicio, String dataTermino,
                      double custo, Imovel imovel) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.custo = custo;
        this.imovel = imovel;
    }

    public Manutencao(double custo, String tipo, String dataInicio, String dataTermino, Imovel imovel) {
        this.custo = custo;
        this.tipo = tipo;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.imovel = imovel;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public double getCusto() {
        return custo;
    }

    public void setValor(double custo) {
        this.custo = custo;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    @Override
    public String toString() {
        return "Manutencao{" +
                "codigo=" + codigo +
                ", tipo='" + tipo + '\'' +
                ", dataInicio='" + dataInicio + '\'' +
                ", dataTermino='" + dataTermino + '\'' +
                ", custo=" + custo +
                ", imovel=" + imovel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manutencao that = (Manutencao) o;
        return codigo == that.codigo && Double.compare(custo, that.custo) == 0 &&
                Objects.equals(tipo, that.tipo) && Objects.equals(dataInicio, that.dataInicio) &&
                Objects.equals(dataTermino, that.dataTermino) && Objects.equals(imovel, that.imovel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, tipo, dataInicio, dataTermino, custo, imovel);
    }
}
