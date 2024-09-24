package com.poo.aluger.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Manutencao implements Serializable {
    private int codigo;
    private  String tipo;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private double custo;
    private  Imovel imovel;

    public Manutencao(int codigo, String tipo, LocalDate dataInicio, LocalDate dataTermino,
                      double custo, Imovel imovel) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.custo = custo;
        this.imovel = imovel;
    }

    public Manutencao(double custo, String tipo, LocalDate dataInicio, LocalDate dataTermino, Imovel imovel) {
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
