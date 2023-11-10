package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Compra {
    private int identificador;
    private LocalDate dataPedido;
    private String docCliente;
    private List<ItemCompra> itensComprados;
    private double valorTotal = 0;
    private double totalPago = 0;

    public Compra(int identificador, LocalDate dataPedido, String docCliente,
            List<ItemCompra> itensComprados) {
        this.identificador = identificador;
        this.dataPedido = dataPedido;
        this.docCliente = docCliente;
        this.itensComprados = new ArrayList<>();
    }

    public Compra(int identificador, LocalDate dataPedido, String docCliente,
            List<ItemCompra> itensComprados, double valorTotal, double totalPago) {
        this.identificador = identificador;
        this.dataPedido = dataPedido;
        this.docCliente = docCliente;
        this.itensComprados = new ArrayList<>();
        this.valorTotal = valorTotal;
        this.totalPago = totalPago;
    }

    public String paraString() {
        return identificador + "," +
                dataPedido + "," +
                docCliente + "," +
                pegarValorTotal() + "," +
                totalPago + "," +
                itensComprados;
    }

    public String paraStringFormatado() {
        return "Identificador: " + identificador + "\n" +
                "Data do Pedido: " + dataPedido + "\n" +
                "Documento do Cliente: " + docCliente + "\n" +
                "Valor Total: " + pegarValorTotal() + "\n" +
                "Total Pago: " + totalPago + "\n" +
                "Itens Comprados: " + itensComprados; 
    }

    public int pegarIdentificador() {
        return identificador;
    }

    public LocalDate pegarDataPedido() {
        return dataPedido;
    }

    public String pegarDocCliente() {
        return docCliente;
    }

    public void adicionarItem(ItemCompra item) {
        itensComprados.add(item);
    }

    public double pegarValorTotal() {
        valorTotal = 0;
        for (ItemCompra item : itensComprados) {
            valorTotal += item.pegarValorTotal();
        }
        return valorTotal;
    }

    public double valorFaltante() {
        return pegarValorTotal() - totalPago;
    }

    public boolean atualizarTotalPago(double valor) {
        if (valorFaltante() >= valor) {
            System.out.println("Valor pago com sucesso");
            totalPago += valor;
            return true;
        } else {
            System.out.println("Valor pago maior que o valor faltante");
            return false;
        }
    }
}
