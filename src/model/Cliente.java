package model;

import java.time.LocalDate;

/**
 * Cliente
 */
public abstract class Cliente {
    // Atributos protegidos para que as classes filhas possam acessar
    protected String nome;
    protected Endereco endereco;
    protected LocalDate dataCadastro; // Classe que representa data YYYY-MM-DD

    public Cliente(String nome, Endereco endereco, LocalDate dataCadastro) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
    }

    public String pegarNome() {
        return this.nome;
    }

    public abstract String paraString();

    public abstract String pegarDocumento();
}
