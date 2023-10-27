package model;

import java.time.LocalDate;

/**
 * PessoaFisica
 */
public class PessoaFisica extends Cliente {
    private String cpf;
    private int qtdMaxParcelas;

    public PessoaFisica(String nome, Endereco endereco, LocalDate dataCadastro, String cpf, int maxParcelas) {
        super(nome, endereco, dataCadastro); // chama o construtor da classe pai
        this.cpf = cpf;
        this.qtdMaxParcelas = maxParcelas;
    }

    public String pegarCpf() {
        return cpf;
    }

    public void setarCpf(String cpf) {
        this.cpf = cpf;
    }

    public int pegarQtdMaxParcelas() {
        return qtdMaxParcelas;
    }

    public void setarQtdMaxParcelas(int qtdMaxParcelas) {
        this.qtdMaxParcelas = qtdMaxParcelas;
    }

    @Override
    public String paraString() {
        return "Nome: " + this.pegarNome() + " Endereço: " + this.endereco.paraString() + " Data de Cadastro: "
                + this.dataCadastro + " CPF: " + this.cpf + " Máximo de Parcelas: " + this.qtdMaxParcelas;
    }

    @Override
    public String pegarDocumento() {
        return this.cpf;
    }
}