package model;

/**
 * PessoaFisica
 */
public class PessoaFisica extends Cliente {
    private String cpf;
    private int maxParcelas;
    
    @Override
    public String paraString(){
        return "Nome: " + this.nome + " Endereço: " + this.endereco.paraString() + " Data de Cadastro: " + this.dataCadastro + " CPF: " + this.cpf + " Máximo de Parcelas: " + this.maxParcelas;
    }
}