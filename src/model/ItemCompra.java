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

    public String paraString() {
        return nomeProduto + "," +
                quantidade + "," +
                precoUnitario;
    }

    public String paraStringFormatado() {
        return "Nome do Produto: " + nomeProduto + "\n" +
                "Quantidade: " + quantidade + "\n" +
                String.format("Preço Unitário: R$ %.2f", precoUnitario) + "\n" +
                String.format("Valor Total: R$ %.2f", pegarValorTotal()) + "\n";
    }

    public double pegarValorTotal() {
        return quantidade * precoUnitario;
    }

    public String nomeProduto() {
        return nomeProduto;
    }

    public double valorTotal() {
        return valorTotal;
    }
}
