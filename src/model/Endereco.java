package model;

/**
 * Endereco
 */
public class Endereco {
    private String rua;
    private int numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;

    public Endereco(String rua, int numero, String bairro, String cep, String cidade, String estado) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String pegarRua() {
        return rua;
    }

    public void setarRua(String rua) {
        this.rua = rua;
    }

    public int pegarNumero() {
        return numero;
    }

    public void setarNumero(int numero) {
        this.numero = numero;
    }

    public String pegarBairro() {
        return bairro;
    }

    public void setarBairro(String bairro) {
        this.bairro = bairro;
    }

    public String pegarCep() {
        return cep;
    }

    public void setarCep(String cep) {
        this.cep = cep;
    }

    public String pegarCidade() {
        return cidade;
    }

    public void setarCidade(String cidade) {
        this.cidade = cidade;
    }

    public String pegarEstado() {
        return estado;
    }

    public void setarEstado(String estado) {
        this.estado = estado;
    }

    public String paraString() {
        return rua + "," + 
               numero + "," + 
               bairro + "," + 
               cep + "," + 
               cidade + "," + 
               estado;
    }

    public String paraStringFormatado() {
        return "Rua: " + rua +
                "\nNúmero: " + numero +
                "\nBairro: " + bairro +
                "\nCEP: " + cep +
                "\nCidade: " + cidade +
                "\nEstado: " + estado;
    }
}
