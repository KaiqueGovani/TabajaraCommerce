package model;

/**
 * Cliente
 */
public abstract class Cliente {
    protected String nome;
    protected Endereco endereco;
    protected int dataCadastro;

    public Cliente(String nome, Endereco endereco, int dataCadastro) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
    }

    public abstract String paraString();
}
