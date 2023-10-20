package model;


public class Endereco {
    private String rua;
    private int numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;

    public String paraString(){
        return "Rua: " + this.rua + " Número: " + this.numero + " Bairro: " + this.bairro + " CEP: " + this.cep + " Cidade: " + this.cidade + " Estado: " + this.estado;
    }
}

