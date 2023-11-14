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

    // Contrutor para criar uma compra vazia
    public Compra(int identificador, LocalDate dataPedido, String docCliente,
            List<ItemCompra> itensComprados) {
        this.identificador = identificador;
        this.dataPedido = dataPedido;
        this.docCliente = docCliente;
        this.itensComprados = new ArrayList<>();
    }

    // Contrutor para criar uma compra existente
    public Compra(int identificador, LocalDate dataPedido, String docCliente,
            List<ItemCompra> itensComprados, double valorTotal, double totalPago) {
        this.identificador = identificador;
        this.dataPedido = dataPedido;
        this.docCliente = docCliente;
        this.itensComprados = itensComprados;
        this.valorTotal = valorTotal;
        this.totalPago = totalPago;
    }

    public String paraString() {
        return identificador + "," +
                dataPedido + "," +
                docCliente + "," +
                pegarValorTotal() + "," +
                totalPago + "," +
                pegarItensComprados();
    }

    public String paraStringFormatado() {
        return "Identificador: " + identificador + "\n" +
                "Data do Pedido: " + dataPedido + "\n" +
                "Documento do Cliente: " + docCliente + "\n" +
                String.format("Valor Total: R$ %.2f", pegarValorTotal()) + "\n" +
                String.format("Total Pago: R$ %.2f", totalPago) + "\n\n" +
                "Itens Comprados: \n" + pegarItensCompradosFormatado(); 
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

    public double pegarValorFaltante() {
        return pegarValorTotal() - totalPago;
    }

    public boolean atualizarTotalPago(double valor) {
        if (pegarValorFaltante() >= valor) {
            System.out.println("Valor pago com sucesso");
            totalPago += valor;
            return true;
        } else {
            System.out.println("Valor pago maior que o valor faltante");
            return false;
        }
    }

    public String pegarItensCompradosFormatado() {
        String itens = "";
        for (ItemCompra item : itensComprados) {
            itens += item.paraStringFormatado();
            itens += "\n";
        }
        return itens;
    }

    public String pegarItensComprados() {
        String itens = "";
        for (int i = 0; i < itensComprados.size(); i++) {
            itens += itensComprados.get(i).paraString();
            if (i < itensComprados.size() - 1) {
                itens += ",";
            }
        }
        return itens;
    }

    public int pegarQuantidadeItens() {
        return itensComprados.size();
    }
}
