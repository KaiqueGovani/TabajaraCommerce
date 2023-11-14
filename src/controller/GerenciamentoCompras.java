package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import model.Compra;
import model.ItemCompra;

/**
 * GerenciamentoCompras
 * 
 * Classe responsável por gerenciar as compras
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

    /**
     * Cria uma compra existente
     * 
     * @param compra
     */
    public void criarCompraExistente(Compra compra) {
        try {
            listCompras.add(compra);
        } catch (Exception e) {
            System.out.println("Erro ao criar compra existente");
            throw e;
        }
    }

    /**
     * Adiciona um item na lista de compras
     * 
     * @param itensComprados
     * @param itemCompra
     */
    public void adicionarItemCompra(List<ItemCompra> itensComprados, ItemCompra itemCompra) {
        try {
            itensComprados.add(itemCompra);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar item na lista de compras");
            throw e;
        }
    }

    /**
     * Adiciona um item na lista de compras por identificador
     * 
     * @param identificador
     * @param itemCompra
     */
    public void adicionarItemCompraPorIdentificador(int identificador, ItemCompra itemCompra) {
        Compra compra = buscarCompraPeloIdentificador(identificador);
        if (compra != null) {
            compra.adicionarItem(itemCompra);
        } else {
            System.out.println("Compra não encontrada");
            throw new RuntimeException("Compra não encontrada");
        }
    }

    /**
     * Cria um item de compra
     * 
     * @param quantidade
     * @param nomeProduto
     * @param precoUnitario
     * @param valorTotal
     * @return o item de compra criado
     */
    public ItemCompra criarItemCompra(int quantidade, String nomeProduto, double precoUnitario, double valorTotal) {
        return new ItemCompra(quantidade, nomeProduto, precoUnitario, valorTotal);
    }

    /**
     * Lista todas as compras em ordem de identificador
     * 
     * @return lista de compras
     */
    public List<Compra> listarCompras() {
        // Retorna uma lista de compras ordenada por identificador
        List<Compra> listaOrdenada = new ArrayList<>(listCompras);
        listaOrdenada.sort((compra1, compra2) -> compra1.pegarIdentificador() - compra2.pegarIdentificador());
        return new ArrayList<>(listaOrdenada);
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
            if (compra.pegarValorFaltante() == 0) {
                System.out.println("Compra já paga");
                throw new RuntimeException("Compra já paga");
            }
            return compra.atualizarTotalPago(totalPago);
        } else {
            System.out.println("Compra não encontrada");
            throw new RuntimeException("Compra não encontrada");
        }
    }

    public Compra buscarCompraMaisCara() {
        Compra compraMaisCara = null;
        for (Compra compra : listCompras) {
            if (compraMaisCara == null || compraMaisCara.pegarValorTotal() < compra.pegarValorTotal()) {
                compraMaisCara = compra;
            }
        }
        return compraMaisCara;
    }

    public Compra buscarCompraMaisBarata() {
        Compra compraMaisBarata = null;
        for (Compra compra : listCompras) {
            if (compraMaisBarata == null || compraMaisBarata.pegarValorTotal() > compra.pegarValorTotal()) {
                compraMaisBarata = compra;
            }
        }
        return compraMaisBarata;
    }

    public double pegarValorFaltantePorIdentificador(int identificador) {
        Compra compra = buscarCompraPeloIdentificador(identificador);
        if (compra != null) {
            return compra.pegarValorFaltante();
        } else {
            System.out.println("Compra não encontrada");
            throw new RuntimeException("Compra não encontrada");
        }
    }

    public String listaComprasParaString(List<Compra> listaCompras) {
        String lista = "";
        for (Compra compra : listaCompras) {
            lista += compra.paraStringFormatado()
                    + "\n\n=====================================================================\n\n";
        }
        return lista;
    }

    public List<Compra> listarComprasPorIdentificador(int identificador) {
        List<Compra> comprasPorIdentificador = new ArrayList<>();
        for (Compra compra : listCompras) {
            if (compra.pegarIdentificador() == identificador) {
                comprasPorIdentificador.add(compra);
            }
        }
        return comprasPorIdentificador;
    }

    public List<Compra> listarComprasNaoPagas() {
        List<Compra> comprasNaoPagas = new ArrayList<>();
        for (Compra compra : listCompras) {
            if (compra.pegarValorFaltante() > 0) {
                comprasNaoPagas.add(compra);
            }
        }
        return comprasNaoPagas;
    }

    public List<Compra> listarComprasUltimasPagas() {
        // Filtrar apenas as compras pagas
        List<Compra> comprasPagas = new ArrayList<>();
        for (Compra compra : listCompras) {
            if (compra.pegarValorFaltante() == 0) {
                comprasPagas.add(compra);
            }
        }

        // Pegar as últimas dez compras (ou menos se houver menos de dez)
        int limite = Math.min(comprasPagas.size(), 10);
        comprasPagas = comprasPagas.subList(0, limite);

        // Inverter a lista para que as compras mais recentes fiquem no início
        Collections.reverse(comprasPagas);
        
        return comprasPagas;
    }

    public List<Compra> buscarComprasUltimos12Meses() {
        List<Compra> comprasUltimos12Meses = new ArrayList<>();
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataLimite = dataAtual.minusMonths(11).withDayOfMonth(1); // Começa do primeiro dia do mês, 12 meses
                                                                            // atrás

        for (Compra compra : listCompras) {
            LocalDate dataCompra = compra.pegarDataPedido();
            // Checa se a compra está dentro do intervalo do mês atual até 12 meses atrás
            if ((dataCompra.isAfter(dataLimite) || dataCompra.isEqual(dataLimite)) &&
                    (dataCompra.isBefore(dataAtual) || dataCompra.getYear() == dataAtual.getYear())) {
                comprasUltimos12Meses.add(compra);
            }
        }
        // Ordena a lista por data
        comprasUltimos12Meses
                .sort((compra1, compra2) -> compra1.pegarDataPedido().compareTo(compra2.pegarDataPedido()));

        System.out.println(listaComprasParaString(comprasUltimos12Meses));
        return comprasUltimos12Meses;
    }

    public String valorTotalUltimos12MesesParaString(List<Compra> listaCompras) {
        HashMap<Integer, String> meses = new HashMap<>();
        meses.put(1, "Janeiro");
        meses.put(2, "Fevereiro");
        meses.put(3, "Março");
        meses.put(4, "Abril");
        meses.put(5, "Maio");
        meses.put(6, "Junho");
        meses.put(7, "Julho");
        meses.put(8, "Agosto");
        meses.put(9, "Setembro");
        meses.put(10, "Outubro");
        meses.put(11, "Novembro");
        meses.put(12, "Dezembro");

        String lista = "";
        HashMap<String, Double> valorTotalPorMesAno = new HashMap<>();

        for (Compra compra : listaCompras) {
            String mesAno = meses.get(compra.pegarDataPedido().getMonthValue()) + "/"
                    + compra.pegarDataPedido().getYear();
            valorTotalPorMesAno.merge(mesAno, compra.pegarValorTotal(), Double::sum);
        }

        for (Compra compra : listaCompras) {
            String mesAno = meses.get(compra.pegarDataPedido().getMonthValue()) + "/"
                    + compra.pegarDataPedido().getYear();
            if (valorTotalPorMesAno.containsKey(mesAno)) {
                double totalMes = valorTotalPorMesAno.remove(mesAno);
                lista += mesAno + " - " + String.format("%.2f", totalMes) + " R$\n";
            }
        }

        if (lista.equals("")) {
            lista = "Não há compras nos últimos 12 meses";
        }

        return lista;
    }

    public void deletarCompra(int id) {
        Compra compra = buscarCompraPeloIdentificador(id);
        if (compra != null) {
            listCompras.remove(compra);
        } else {
            System.out.println("Compra não encontrada");
            throw new RuntimeException("Compra não encontrada");
        }
    }

    public void realocarCompra(Compra compra) {
        listCompras.remove(compra);
        listCompras.add(compra);
    }

    public void verificarCompraFinalizavel(int id) {
        Compra compra = buscarCompraPeloIdentificador(id);
        if (compra != null) {
            if (compra.pegarQuantidadeItens() == 0) {
                System.out.println("Compra não finalizável");
                throw new RuntimeException("Nenhum item adicionado à compra, cancelando!");
            }
        } else {
            System.out.println("Compra não encontrada");
            throw new RuntimeException("Compra não encontrada");
        }
    }

    public void fecharCompra(int identificador) {
        Compra compra = buscarCompraPeloIdentificador(identificador);
        if (compra != null) {
            realocarCompra(compra);
        } else {
            throw new RuntimeException("Não foi possível fechar a compra");   
        }
    }
}
