package model;

import java.time.LocalDate;

public class PessoaJuridica extends Cliente {
    private String cnpj;
    private String razaoSocial;
    private int prazoPagamento; // em dias

    public PessoaJuridica(String nome, Endereco endereco, LocalDate dataCadastro, String cnpj, String razaoSocial, int prazoPagamento){
        super(nome, endereco, dataCadastro); // chama o construtor da classe pai
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.prazoPagamento = prazoPagamento;
    }

    @Override
    public String paraString(){
        return "Nome: " + this.nome + " Endereço: " + this.endereco.paraString() + " Data de Cadastro: "
                + this.dataCadastro + " CNPJ: " + this.cnpj + " Razão Social: " + this.razaoSocial + " Prazo de Pagamento: " + this.prazoPagamento;
    }
}
