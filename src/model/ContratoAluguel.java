package model;

import java.io.Serializable;

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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
}
