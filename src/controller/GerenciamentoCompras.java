package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Compra;
import model.ItemCompra;

/**
 * GerenciamentoCompras
 */
public class GerenciamentoCompras {
    private List<Compra> listCompras;

    /**
     * Construtor
     */
    public GerenciamentoCompras() {
        this.listCompras = new ArrayList<>();
    }

    /**
     * Cadastra uma compra
     * 
     * @param novaCompra
     * @return true se a compra foi cadastrada, false caso contrário
     */
    public int cadastrarCompra(String docCliente) {
        try {
            Compra novaCompra = new Compra(listCompras.size() + 1, LocalDate.now(), docCliente,
                    new ArrayList<ItemCompra>());
            listCompras.add(novaCompra);
            System.out.println("Nova Compra Vazia criada com sucesso");

            return novaCompra.pegarIdentificador();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar nova compra" + e.getMessage());
            throw e;
        }
    }

    public void criarCompraExistente(Compra compra) {
        try {
            listCompras.add(compra);
        } catch (Exception e) {
            System.out.println("Erro ao criar compra existente");
            throw e;
        }
    }

    public void adicionarItemCompra(List<ItemCompra> itensComprados, ItemCompra itemCompra) {
        try {
            itensComprados.add(itemCompra);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar item na lista de compras");
            throw e;
        }
    }

    public void adicionarItemCompraPorIdentificador(int identificador, ItemCompra itemCompra) {
        Compra compra = buscarCompraPeloIdentificador(identificador);
        if (compra != null) {
            compra.adicionarItem(itemCompra);
        } else {
            System.out.println("Compra não encontrada");
            throw new RuntimeException("Compra não encontrada");
        }
    }

    public ItemCompra criarItemCompra(int quantidade, String nomeProduto, double precoUnitario, double valorTotal) {
        return new ItemCompra(quantidade, nomeProduto, precoUnitario, valorTotal);
    }

    public List<Compra> listarCompras() {
        return new ArrayList<>(this.listCompras); // Retorna uma lista de compras
    }

    /**
     * Busca uma compra pelo identificador
     * 
     * @param identificador
     * @return a compra se encontrar, null caso contrário
     */
    public Compra buscarCompraPeloIdentificador(int identificador) {
        for (Compra compra : listCompras) {
            if (compra.pegarIdentificador() == identificador) {
                return compra;
            }
        }
        return null;
    }

    /**
     * Busca valor faltante por uma lista de compra
     * 
     * @param valorFaltante
     * @return lista com valor faltante maior que 0, null caso contrário
     */
    public List<Compra> listaComprasComValorFaltante(double valorFaltante) {
        List<Compra> comprasComValorFaltante = new ArrayList<>();
        for (Compra novaCompra : listCompras) {
            if (novaCompra.pegarValorFaltante() > 0) {
                comprasComValorFaltante.add(novaCompra);
            }
        }
        return comprasComValorFaltante;
    }

    /**
     * @param identificador o identificador da compra a ser atualizada
     * @param totalPago     o novo valor total pago a ser atualizado
     * @return true se a atualização foi bem-sucedida, false caso contrário
     */
    public boolean atualizaValorFaltante(int identificador, double totalPago) {
        Compra compra = buscarCompraPeloIdentificador(identificador);
        if (compra != null) {
            compra.atualizarTotalPago(totalPago);
            return true;
        } else {
            System.out.println("Compra não encontrada");
            throw new RuntimeException("Compra não encontrada");
        }
    }
}
