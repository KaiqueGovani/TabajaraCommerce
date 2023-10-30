package model;

import java.time.LocalDate;

/**
 * PessoaFisica
 */
public class PessoaFisica extends Cliente {
    private String cpf;
    private int qtdMaxParcelas;

    public PessoaFisica(String nome, LocalDate dataCadastro, String cpf, int maxParcelas, Endereco endereco) {
        super(nome, dataCadastro, endereco); // chama o construtor da classe pai
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
        return "PF," + nome + "," +
               dataCadastro + "," +
               cpf + "," +
               qtdMaxParcelas + "," +
               endereco.paraString();
    }

    @Override
    public String paraStringFormatado() {
        return "Nome: " + nome +
                "\nData de Cadastro: "  + dataCadastro + 
                "\nCPF: " + cpf + 
                "\nMáximo de Parcelas: " + qtdMaxParcelas + 
                "\nEndereço: " + endereco.paraStringFormatado();
    }

    @Override
    public String pegarDocumento() {
        return cpf;
    }

}