package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Compra {
    private int identificador;
    private LocalDate dataPedido;
    private double valorTotal;
    private String docCliente;
    private List<ItemCompra> itensComprados;
    private double totalPago;

    public Compra(int identificador, LocalDate dataPedido, double valorTotal, String docCliente,
            List<ItemCompra> itensComprados, double totalPago) {
        this.identificador = identificador;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
        this.docCliente = docCliente;
        this.itensComprados = new ArrayList<>();
        this.totalPago = totalPago;
    }

    public String paraString() {
        return identificador + "," + 
               dataPedido + "," + 
               valorTotal + "," + 
               docCliente + "," + 
               itensComprados + "," + 
               totalPago;
    }

    public int pegarIdentificador() {
        return identificador;
    }

    public LocalDate  pegarDataPedido() {
        return dataPedido;
    }

    public double pegarTotal() {
        return valorTotal;
    }

    public String cliente() {
        return docCliente;
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