package model;

public class Pagamento {
    private float valor;
    private String dataPagamento;
    private String formaPagamento;
    private  ContratoAluguel contratoAluguel;

    public Pagamento(float valor, String dataPagamento, String formaPagamento, ContratoAluguel contratoAluguel) {
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.formaPagamento = formaPagamento;
        this.contratoAluguel = contratoAluguel;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
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
}
