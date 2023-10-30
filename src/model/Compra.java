package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Compra {
    private String identificador;
    private LocalDate dataPedido;
    private double valorTotal;
    private Cliente cliente;
    private List<ItemCompra> itensComprados;
    private double totalPago;

    public Compra(String identificador, LocalDate dataPedido, double valorTotal, Cliente cliente,
            List<ItemCompra> itensComprados, double totalPago) {
        this.identificador = identificador;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
        this.itensComprados = new ArrayList<>();
        this.totalPago = totalPago;
    }

    public String paraString() {
        return identificador + "," + 
               dataPedido + "," + 
               valorTotal + "," + 
               cliente + "," + 
               itensComprados + "," + 
               totalPago;
    }

    public String pegarIdentificador() {
        return identificador;
    }

    public LocalDate  pegarDataPedido() {
        return dataPedido;
    }

    public double pegarTotal() {
        return valorTotal;
    }

    public Cliente cliente() {
        return cliente;
    }

    public void adicionarItem(ItemCompra item) {
        itensComprados.add(item);
    }

    public double pegarValorTotal() {
        double total = 0;
        for (ItemCompra item : itensComprados) {
            total += item.pegarValorTotal();
        }
        return total;
    }

    public double valorFaltante() {
        return pegarValorTotal() - totalPago;
    }
}