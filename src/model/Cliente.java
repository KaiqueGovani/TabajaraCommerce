package model;

/**
 * Cliente
 */
public abstract class Cliente {
    protected String nome;
    protected Endereco endereco;
    protected int dataCadastro;

    public abstract String paraString();
}

