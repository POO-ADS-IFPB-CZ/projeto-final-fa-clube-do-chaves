package model;

import java.io.Serializable;
import java.util.Objects;

public class Pagamento  implements Serializable {
    private int codigo;
    private float valor;
    private String dataPagamento;
    private String formaPagamento;
    private  ContratoAluguel contratoAluguel;

    public Pagamento(int codigo, float valor, String dataPagamento, String formaPagamento,
                     ContratoAluguel contratoAluguel) {
        this.codigo = codigo;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.formaPagamento = formaPagamento;
        this.contratoAluguel = contratoAluguel;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return codigo == pagamento.codigo && Float.compare(valor, pagamento.valor) == 0
                && Objects.equals(dataPagamento, pagamento.dataPagamento)
                && Objects.equals(formaPagamento, pagamento.formaPagamento) &&
                Objects.equals(contratoAluguel, pagamento.contratoAluguel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, valor, dataPagamento, formaPagamento, contratoAluguel);
    }
}
