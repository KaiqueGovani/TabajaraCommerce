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

    public String paraString() {
        return "Rua: " + rua +
                "- Número: " + numero +
                "- Bairro: " + bairro +
                "- CEP: " + cep +
                "- Cidade: " + cidade +
                "- Estado: " + estado;
    }
}
