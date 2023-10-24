package model;

public class Produto {
    private int codigo;
    private String nome;
    private String descricao;
    private double preco;

    public String paraString() {
        return "Produto: " +
                ", Codigo: " + codigo +
                ", Nome: " + nome +
                ", Descricao: " + descricao +
                ", Preco: " + preco;
    }

    public int pegarCodigo() {
        return codigo;
    }

    public void setarCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String pegarNome() {
        return nome;
    }

    public void setarNome(String nome) {
        this.nome = nome;
    }

    public String pegarDescricao() {
        return descricao;
    }

    public void setarDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double pegarPreco() {
        return preco;
    }

    public void setarPreco(double preco) {
        this.preco = preco;
    }
}
