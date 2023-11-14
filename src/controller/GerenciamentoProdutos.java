package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import model.ProdutoPerecivel;

public class GerenciamentoProdutos {

    private List<Produto> produtos;

    public GerenciamentoProdutos() {
        this.produtos = new ArrayList<>();
    }

    public boolean cadastrarProdutos(Produto produto) {
        if (buscarPeloNome(produto.pegarNome())) {
            System.out.println("Produto já existe");
            return false;
        }
        produtos.add(produto);
        System.out.println("Produto adicionado com sucesso");
        System.out.println(produto.paraString());
        return true;
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<>(this.produtos);
    }

    public boolean buscarPeloNome(String nome) {
        return produtos.stream()
                .anyMatch(produto -> produto.pegarNome().equals(nome));
    }

    public List<ProdutoPerecivel> listarProdutosPereciveis() {
        List<ProdutoPerecivel> produtosPereciveis = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto instanceof ProdutoPerecivel) {
                produtosPereciveis.add((ProdutoPerecivel) produto);
            }
        }
        return produtosPereciveis;
    }

    public List<ProdutoPerecivel> listarProdutosVencidos() {
        List<ProdutoPerecivel> produtosVencidos = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto instanceof ProdutoPerecivel) {
                if (((ProdutoPerecivel) produto).estaVencido()) {
                    produtosVencidos.add((ProdutoPerecivel) produto);
                }
            }
        }
        return produtosVencidos;
    }

    public Produto criarProduto(String nome, double preco, String descricao){
        Produto novoProduto = new Produto(nome, preco, descricao);
        System.out.println("Produto criado com sucesso!");
        novoProduto.setarCodigo(produtos.size() + 1);
        return novoProduto;
    }

    public ProdutoPerecivel criarProdutoPerecivel(String nome, double preco, String descricao, LocalDate dataValidade){
        ProdutoPerecivel novoProdutoPerecivel = new ProdutoPerecivel(nome, preco, descricao, dataValidade);
        System.out.println("Produto perecivel adicionado com sucesso!");
        novoProdutoPerecivel.setarCodigo(produtos.size() + 1);
        return novoProdutoPerecivel;
    }

    public LocalDate criarLocalDate(String dataString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dataString, formatter);
    }
    
    public String ListaProdutosParaString(List<Produto> listaProdutos) {
    String lista = "";
    for (Produto produto : listaProdutos) {
        lista += produto.paraStringFormatado() + "\n\n==============================================================================\n\n";
    }
    return lista;
    }

    public List<Produto> listarProdutosPorNome(String nome) {
        List<Produto> produtosPorNome = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.pegarNome()
                    .toLowerCase()
                    .startsWith(nome.toLowerCase())) {
                produtosPorNome.add(produto);
            }
        }
        return produtosPorNome;
    }
}


    
