package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Produto;
import model.ProdutoPerecivel;

// ! Estava faltando importar os módulos aqui em cima !


//! Seria bom adicionar docstrings nos métodos, para facilitar a leitura do código.
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
        return new ArrayList<>(this.produtos); // !Criar cópia da lista para evitar que a lista original seja modificada.
    }

    public boolean buscarPeloNome(String nome) {
        return produtos.stream()
                .anyMatch(produto -> produto.pegarNome().equals(nome));
    }

    public List<ProdutoPerecivel> listarProdutosPereciveis() {
        LocalDate dataAtual = LocalDate.now();
        //! Há maneiras mais simples e visíveis de fazer isso, igual em gerenciamentoClientes.
        return produtos.stream()
                .filter(produto -> produto instanceof ProdutoPerecivel)
                .map(produto -> (ProdutoPerecivel) produto) // ! Talvez não faça sentido fazer esse cast, já que a linha de cima já garante que o produto é perecível.
                .filter(produtoPerecivel -> produtoPerecivel.estaVencido()
                        && produtoPerecivel.pegarDataDeValidade().isBefore(dataAtual)) //!Está vencido já é o suficiente, essa parte não tem necessidade.
                .collect(Collectors.toList());
    }

}
