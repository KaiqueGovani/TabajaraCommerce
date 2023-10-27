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

    public abstract String paraString();

    public abstract String pegarDocumento();

    public String pegarNome() {
        return nome;
    }

    public void setarNome(String nome) {
        this.nome = nome;
    }

    public Endereco pegarEndereco() {
        return endereco;
    }

    public void setarEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public LocalDate pegarDataCadastro() {
        return dataCadastro;
    }

    public void setarDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
