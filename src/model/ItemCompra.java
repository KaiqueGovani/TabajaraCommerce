package model;

public class ItemCompra {
    private int quantidade;
    private String nomeProduto;
    private double precoUnitario;
    private double valorTotal;

    public ItemCompra(int quantidade, String nomeProduto, double precoUnitario, double valorTotal) {
        this.quantidade = quantidade;
        this.nomeProduto = nomeProduto;
        this.precoUnitario = precoUnitario;
        this.valorTotal = quantidade * precoUnitario;
    }

    public double pegarValorTotal() {
        return quantidade * precoUnitario;
    }
}