package controller;

private List<Produto> produtos;

public GerenciamentoProdutos() {
    this.produtos = new ArrayList <>();
}

public boolean cadastrarProdutos(Produto produto) {
    if(buscarPeloNome(produto.pegarNome())){
        System.out.prinln("Produto já existe");
        return false;
    }
    produtos.add(produto);
    System.out.println("Produto adicionado com sucesso");
    System.out.println(produto.paraString());
    return true;
}

public List<Produto> listarProdutos() {
    return this.produtos;
}

public boolean buscarPeloNome(String nome) {
    return produtos.stream()
            .anyMatch(produto -> produto.pegarNome().equals(nome));
}

public List<ProdutoPerecivel> listaProdutosPereciveis() {
    LocalDate dataAtual = LocalDate.now();

    return produtos.steam()
            .filter(produto -> produto instanceof ProdutoPerecivel)
            .map(produto -> (ProdutoPerecivel) produto)
            .filter(produtoPerecivel -> produtoPerecivel.estaVencido() && produtoPerecivel.pegarDataDeValidade().isBefore(dataAtual))
            .collect(Collectors.toList());
}


