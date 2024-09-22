package com.poo.aluger.model;

import java.io.Serializable;
import java.util.Objects;

public class Inquilino  implements Serializable{
    private int codigo;
    private  String nome;
    private  String cpf;
    private  String telefone1;
    private  String telefone2;

    public Inquilino(int codigo, String nome, String cpf, String telefone1, String telefone2) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
    }

    public Inquilino(String nome, String cpf, String telefone1, String telefone2) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    @Override
    public String toString() {
        return "Inquilino{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone1='" + telefone1 + '\'' +
                ", telefone2='" + telefone2 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inquilino inquilino = (Inquilino) o;
        return codigo == inquilino.codigo && Objects.equals(nome, inquilino.nome) &&
                Objects.equals(cpf, inquilino.cpf) && Objects.equals(telefone1, inquilino.telefone1)
                && Objects.equals(telefone2, inquilino.telefone2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nome, cpf, telefone1, telefone2);
    }
}
