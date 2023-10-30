package controller;

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
    //TODO método para Cadastrar uma nova Compra
    public boolean cadastrarCompra(Compra novaCompra) {
        listCompras.add(novaCompra);
        System.out.println("Lista de Compra cadastrada com sucesso");
        System.out.println(novaCompra.paraString());
        return true;
    }

    public ItemCompra criarCompra (int quantidade, String nomeProduto, double precoUnitario, double valorTotal) {
        return new ItemCompra(quantidade, nomeProduto, precoUnitario, valorTotal);
    }

    //TODO Método que retorne a lista de compras como um new ArrayList
    public List<Compra> listarCompras() {
        return new ArrayList<>(this.listCompras); // Retorna uma lista de compras
    }

    //TODO Método que Busca uma compra pelo identificador e a retorna
    /**
     * Busca uma compra pelo identificador
     * 
     * @param identificador
     * @return a compra se encontrar, null caso contrário
     */
    public List<Compra> listarCompraPeloIdentificador(String identificador) {
        List<Compra> compraPeloIdentificador = new ArrayList<>();
        for (Compra novaCompra : listCompras) {
            if (novaCompra.pegarIdentificador()
                    .toLowerCase(null)      //Usado para converter string em minúsculas e não ser sensívvel a maiúsculas e minúsculas
                    .startsWith(identificador.toLowerCase(null))) {     //Usado para verificar se uma string começa com otura string especificada
                compraPeloIdentificador.add(novaCompra);
            }
        }
        return compraPeloIdentificador;
    }

    //TODO Método que retorna uma lista de compras que estão com o valor faltante maior que 0
    /**
     * Busca valor faltante por uma lista de compra
     * 
     * @param valorFaltante
     * @return lista com valor faltante maior que 0, null caso contrário
     */
    public List<Compra> listaComprasComValorFaltante(double valorFaltante) {
        List<Compra> comprasComValorFaltante = new ArrayList<>();
        for (Compra novaCompra : listCompras) {
            if (novaCompra.valorFaltante() > 0) {
                comprasComValorFaltante.add(novaCompra);
            }
        }
        return comprasComValorFaltante;
    }
    //TODO Método que atualiza o valor pago de uma compra
    /**
     * @param identificador o identificador da compra a ser atualizada
     * @param totalPago o novo valor total pago a ser atualizado
     * @return true se a atualização foi bem-sucedida, false caso contrário
     */
    public boolean atualizaValorFaltante(String identificador, double totalPago) {
        for (Compra novaCompra : listCompras) {
            if (novaCompra.pegarIdentificador().equals(identificador)) {
                novaCompra.valorFaltante();
                return true;
            }
        }
        return false;
    }
}
