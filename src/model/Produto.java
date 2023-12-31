package model;

public class Produto {
    private int codigo;
    private String nome;
    private String descricao;
    private double preco;

    public Produto(String nome, double preco, String descricao){
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }

    public Produto(int codigo, String nome, double preco, String descricao){
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }

    public String paraStringFormatado() {
        return "Produto: " +
                "\nCodigo: " + codigo +
                "\nNome: " + nome +
                "\nDescricao: " + descricao +
                "\nPreco: R$ " + String.format("%.2f", preco);
    }

    public String paraString() {
        return codigo + "," +
                nome + "," +
                descricao + "," +
                preco;
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
