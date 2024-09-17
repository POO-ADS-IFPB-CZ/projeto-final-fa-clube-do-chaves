package model;

public class Imovel{
    private byte[] fotos;
    private String tipo;
    private double areaTotal;
    private int quantidadeQuartos;
    private String status;
    private int quantidadeBanheiros;
    private String descricao;
    private float valorAluguel;
    private Proprietario proprietario;
    // Endereço
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;

    public Imovel(byte[] fotos, String tipo, double areaTotal, int quantidadeQuartos,
                  String status, int quantidadeBanheiros, String descricao, float valorAluguel,
                  Proprietario proprietario, String rua, int numero, String bairro,
                  String cidade, String estado) {
        this.fotos = fotos;
        this.tipo = tipo;
        this.areaTotal = areaTotal;
        this.quantidadeQuartos = quantidadeQuartos;
        this.status = status;
        this.quantidadeBanheiros = quantidadeBanheiros;
        this.descricao = descricao;
        this.valorAluguel = valorAluguel;
        this.proprietario = proprietario;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public byte[] getFotos() {
        return fotos;
    }

    public void setFotos(byte[] fotos) {
        this.fotos = fotos;
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

    public int getQuantidadeQuartos() {
        return quantidadeQuartos;
    }

    public void setQuantidadeQuartos(int quantidadeQuartos) {
        this.quantidadeQuartos = quantidadeQuartos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantidadeBanheiros() {
        return quantidadeBanheiros;
    }

    public void setQuantidadeBanheiros(int quantidadeBanheiros) {
        this.quantidadeBanheiros = quantidadeBanheiros;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(float valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
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
}
