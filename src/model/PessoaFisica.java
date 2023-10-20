package model;

import java.time.LocalDate;

/**
 * PessoaFisica
 */
public class PessoaFisica extends Cliente {
    private String cpf;
    private int maxParcelas;

    public PessoaFisica(String nome, Endereco endereco, LocalDate dataCadastro, String cpf, int maxParcelas) {
        super(nome, endereco, dataCadastro); // chama o construtor da classe pai
        this.cpf = cpf;
        this.maxParcelas = maxParcelas;
    }

    @Override
    public String paraString() {
        return "Nome: " + this.nome + " Endereço: " + this.endereco.paraString() + " Data de Cadastro: "
                + this.dataCadastro + " CPF: " + this.cpf + " Máximo de Parcelas: " + this.maxParcelas;
    }
}