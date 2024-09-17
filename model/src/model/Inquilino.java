package model;

public class Inquilino {
    private  String nome;
    private  String cpf;
    private  String telefone1;
    private  String telefone2;

    public Inquilino(String nome, String cpf, String telefone1, String telefone2) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
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
}
