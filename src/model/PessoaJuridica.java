package model;

import java.time.LocalDate;

public class PessoaJuridica extends Cliente {
    private String cnpj;
    private String razaoSocial;
    private int prazoPagamento; // em dias

    public PessoaJuridica(String nome, LocalDate dataCadastro, String cnpj, String razaoSocial, int prazoPagamento, Endereco endereco) {
        super(nome, dataCadastro, endereco); // chama o construtor da classe pai
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.prazoPagamento = prazoPagamento;
    }

    public String pegarCnpj(){
        return cnpj;
    }

    public void setarCnpj(String cnpj){
        this.cnpj = cnpj;
    }

    public String pegarSocial(){
        return razaoSocial;
    }

    public void setarSocial(String razaoSocial){
        this.razaoSocial = razaoSocial;
    }

    public int pegarPrazo(){
        return prazoPagamento;
    }

    public void setarPrazo(int prazoPagamento){
        this.prazoPagamento = prazoPagamento;
    }

    @Override
    public String paraString(){
        return "PJ," + nome + "," +
               dataCadastro + "," +
               cnpj + "," +
               razaoSocial + "," +
               prazoPagamento + "," +
               endereco.paraString();
    }

    @Override
    public String paraStringFormatado() {
        return "Nome Fantasia: " + nome +
               "\nData de Cadastro: " + dataCadastro +
               "\nCNPJ: " + cnpj +
               "\nRazão Social: " + razaoSocial +
               "\nPrazo de Pagamento: " + prazoPagamento + " dias" +
               "\nEndereço: " + endereco.paraStringFormatado();
    }

    @Override
    public String pegarDocumento() {
        return this.cnpj;
    }
}
