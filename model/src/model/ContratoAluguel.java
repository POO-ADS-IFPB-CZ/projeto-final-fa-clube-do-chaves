package model;

public class ContratoAluguel {
    private  String dataInicio;
    private String dataTermino;
    private float valorAluguel;
    private String diaPagamento;
    private  Inquilino inquilino;
    private  Imovel imovel;

    public ContratoAluguel(String dataInicio, String dataTermino, float valorAluguel,
                           String diaPagamento, Inquilino inquilino, Imovel imovel) {
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.valorAluguel = valorAluguel;
        this.diaPagamento = diaPagamento;
        this.inquilino = inquilino;
        this.imovel = imovel;
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
}
