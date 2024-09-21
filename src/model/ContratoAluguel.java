package model;

import java.io.Serializable;
import java.util.Objects;

public class ContratoAluguel implements Serializable {
    private  int codigo;
    private  String dataInicio;
    private String dataTermino;
    private float valorAluguel;
    private String diaPagamento;
    private  Inquilino inquilino;
    private  Imovel imovel;
    private  Proprietario proprietario;

    public ContratoAluguel(int codigo, String dataInicio, String dataTermino,
                           float valorAluguel, String diaPagamento, Inquilino inquilino,
                           Imovel imovel, Proprietario proprietario) {
        this.codigo = codigo;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.valorAluguel = valorAluguel;
        this.diaPagamento = diaPagamento;
        this.inquilino = inquilino;
        this.imovel = imovel;
        this.proprietario = proprietario;
    }
    public ContratoAluguel(){}

    public int getCodigo() {
        return codigo;
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

    public float getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(float valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public String getDiaPagamento() {
        return diaPagamento;
    }

    public void setDiaPagamento(String diaPagamento) {
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
    public String toString() {
        return "ContratoAluguel{" +
                "codigo=" + codigo +
                ", dataInicio='" + dataInicio + '\'' +
                ", dataTermino='" + dataTermino + '\'' +
                ", valorAluguel=" + valorAluguel +
                ", diaPagamento='" + diaPagamento + '\'' +
                ", inquilino=" + inquilino +
                ", imovel=" + imovel +
                ", proprietario=" + proprietario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContratoAluguel that = (ContratoAluguel) o;
        return codigo == that.codigo && Float.compare(valorAluguel, that.valorAluguel) == 0 &&
                Objects.equals(dataInicio, that.dataInicio) && Objects.equals(dataTermino, that.dataTermino)
                && Objects.equals(diaPagamento, that.diaPagamento) && Objects.equals(inquilino, that.inquilino)
                && Objects.equals(imovel, that.imovel) && Objects.equals(proprietario, that.proprietario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, dataInicio, dataTermino, valorAluguel, diaPagamento,
                inquilino, imovel, proprietario);
    }
}
