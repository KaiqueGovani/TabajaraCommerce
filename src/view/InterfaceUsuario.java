package view;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.GerenciamentoClientes;
import controller.GerenciamentoCompras;
import controller.GerenciamentoProdutos;

/**
 * InterfaceUsuario
 */
public class InterfaceUsuario {
    private GerenciamentoClientes gClientes;
    private GerenciamentoCompras gCompras;
    private GerenciamentoProdutos gProdutos;

    public InterfaceUsuario(GerenciamentoClientes gClientes, GerenciamentoCompras gCompras,
            GerenciamentoProdutos gProdutos) {
        this.gClientes = gClientes;
        this.gCompras = gCompras;
        this.gProdutos = gProdutos;
    }

    public static void mostrarErro(String msg, String titulo) {
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public static void mostrarInfo(String msg, String titulo) {
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarAlerta(String msg, String titulo) {
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.WARNING_MESSAGE);
    }

    public static void mostrarMensagem(String msg, String titulo) {
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    public static void mostrarMensagemGrande(String mensagem, String titulo) {
        JTextArea textArea = new JTextArea(16, 50); // Ajuste o número de linhas e colunas conforme necessário.
        textArea.setText(mensagem); // Seta o texto na JTextArea.
        textArea.setEditable(false); // Se você quer que o texto seja não-editável.
        textArea.setWrapStyleWord(true); // Quebra de linha por palavras para não cortar palavras ao meio.
        textArea.setLineWrap(true); // Ativa a quebra automática de linha.

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JOptionPane.showMessageDialog(null, scrollPane, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void avisoSaindo() {
        JOptionPane.showMessageDialog(null, "Saindo da aplicação", "Saindo", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Pega o valor digitado pelo usuário
     * 
     * @param msg
     * @param titulo
     * @return String do valor digitado
     */
    public static String pegarValorDigitado(String msg, String titulo) {
        String valorDigitado;
        do {
            valorDigitado = JOptionPane.showInputDialog(null, msg, titulo, JOptionPane.PLAIN_MESSAGE);
            if (valorDigitado == null) {
                return null;
            }
            if (valorDigitado.equals("")) {
                mostrarAlerta("Entrada inválida!", titulo);
            }
        } while (valorDigitado.equals(""));

        return valorDigitado;
    }

    public String menuPrincipal() {
        String menu = "1. Cadastros de Clientes\n" +
                "2. Deletar cliente pelo CPF ou CNPJ\n" +
                "3. Deletar cliente pelo nome\n" +
                "4. Cadastro de Produtos\n" +
                "5. Efetuação de uma compra\n" +
                "6. Atualização da situação de pagamento de uma compra\n" +
                "7. Relatórios\n" +
                "8. Sair (termina o sistema)\n" +
                "Digite o número da opção desejada:";

        String valorSelecionado = JOptionPane.showInputDialog(null, menu, "Menu Principal", JOptionPane.PLAIN_MESSAGE);

        if (valorSelecionado == null) {
            return "8";
        }

        return valorSelecionado;
    }

    public void cadastrarCliente() {
        String menu = "1. Pessoa Física\n" +
                "2. Pessoa Jurídica\n" +
                "Digite o número da opção desejada:";

        String valorSelecionado = JOptionPane.showInputDialog(null, menu, "Cadastro de Clientes",
                JOptionPane.PLAIN_MESSAGE);

        String titulo = "Cadastro de Cliente";
        if (valorSelecionado != null) {
            try {
                int opcaoInt = Integer.parseInt(valorSelecionado);
                if (opcaoInt >= 1 && opcaoInt <= 2) {
                    String nome = pegarValorDigitado("Digite o nome do cliente:", titulo);
                    String rua = pegarValorDigitado("Digite a rua do cliente:", titulo);
                    int numero = Integer.parseInt(pegarValorDigitado("Digite o número da casa:", titulo));
                    String bairro = pegarValorDigitado("Digite o bairro do cliente:", titulo);
                    String cep = pegarValorDigitado("Digite o CEP do cliente:", titulo);
                    String cidade = pegarValorDigitado("Digite a cidade do cliente:", titulo);
                    String estado = pegarValorDigitado("Digite o estado do cliente:", titulo);

                    switch (opcaoInt) {
                        case 1: // Pessoa Física
                            String cpf = pegarValorDigitado("Digite o CPF do cliente:", titulo);
                            int qtdMaxParcelas = Integer.parseInt(
                                    pegarValorDigitado("Digite a quantidade máxima de parcelas do cliente:", titulo));

                            gClientes.cadastrarCliente(gClientes.criarPessoaFisica(
                                    nome,
                                    gClientes.criarEndereco(rua, numero, bairro, cep, cidade, estado),
                                    cpf,
                                    qtdMaxParcelas));

                            mostrarMensagem("Cliente cadastrado com sucesso!", titulo);
                            break;
                        case 2: // Pessoa Jurídica
                            String cnpj = pegarValorDigitado("Digite o CNPJ do cliente:", titulo);
                            String razaoSocial = pegarValorDigitado("Digite a razão social do cliente:", titulo);
                            int prazoPagamento = Integer
                                    .parseInt(pegarValorDigitado("Digite o prazo de pagamento do cliente:", titulo));

                            gClientes.cadastrarCliente(gClientes.criarPessoaJuridica(
                                    nome,
                                    gClientes.criarEndereco(rua, numero, bairro, cep, cidade, estado),
                                    cnpj,
                                    razaoSocial,
                                    prazoPagamento));

                            mostrarMensagem("Cliente cadastrado com sucesso!", titulo);

                            break;

                        default:

                            break;
                    }
                } else {
                    mostrarErro("Opção inválida!", "Erro");
                }
            } catch (NumberFormatException e) {
                mostrarErro("Entrada inválida!", "Erro");
            }
        }
    }

    public void deletarClientePorDocumento() {
        String titulo = "Deletar Cliente";
        String documento = pegarValorDigitado("Digite o documento do cliente:", titulo);
        if (gClientes.deletarPeloDocumento(documento)) {
            mostrarMensagem("Cliente deletado com sucesso!", titulo);
        } else {
            mostrarErro("Cliente não encontrado!", titulo);
        }
    }

    public void deletarClientePorNome() {
        String titulo = "Deletar Cliente";
        String nome = pegarValorDigitado("Digite o nome do cliente:", titulo);
        System.out.println(nome);
        if (gClientes.deletarPeloNome(nome)) {
            mostrarMensagem("Cliente deletado com sucesso!", titulo);
        } else {
            mostrarErro("Cliente não encontrado!", titulo);
        }
    }

    public void cadastrarProduto() {
        String menu = "1. Produto\n" +
                "2. Produto Perecível\n" +
                "Digite o número da opção desejada:";

        String valorSelecionado = JOptionPane.showInputDialog(null, menu, "Cadastro de Produtos",
                JOptionPane.PLAIN_MESSAGE);

        String titulo = "Cadastro de Produto";
        if (valorSelecionado != null) {
            try {
                int opcaoInt = Integer.parseInt(valorSelecionado);
                if (opcaoInt >= 1 && opcaoInt <= 2) {
                    String nome = pegarValorDigitado("Digite o nome do produto:", titulo);
                    double preco = Double.parseDouble(pegarValorDigitado("Digite o preço do produto:", titulo));
                    String descricao = pegarValorDigitado("Digite a descrição do produto:", titulo);

                    switch (opcaoInt) {
                        case 1: // Produto
                            gProdutos.cadastrarProdutos(gProdutos.criarProduto(nome, preco, descricao));
                            mostrarMensagem("Produto cadastrado com sucesso!", titulo);
                            break;
                        case 2: // Produto Perecível
                            String dataValidade = pegarValorDigitado(
                                    "Digite a data de validade do produto (dd/mm/yyyy):", titulo);

                            gProdutos.cadastrarProdutos(gProdutos.criarProdutoPerecivel(nome, preco, descricao,
                                    gProdutos.criarLocalDate(dataValidade)));
                            mostrarMensagem("Produto perecível cadastrado com sucesso!", titulo);
                            break;
                        default:
                            break;
                    }
                } else {
                    mostrarErro("Opção inválida!", "Erro");
                }
            } catch (NumberFormatException e) {
                mostrarErro("Entrada inválida!", "Erro");
            }
        }
    }

    public void efetuarCompra() {
        String titulo = "Efetuar uma Compra";
        try {
            String docCliente = pegarValorDigitado("Digite o documento do cliente: (CPF/CNPJ)", titulo);
            if (docCliente == null) {
                return;
            }

            int id = gCompras.cadastrarCompra(docCliente);
            if (id > 0) { // Se a compra foi cadastrada com sucesso
                int valorSelecionado;
                do {
                    String menu = "1. Adicionar item\n" +
                            "2. Finalizar compra\n" +
                            "Digite o número da opção desejada:";
                    titulo = "Cadastrar um produto";

                    valorSelecionado = Integer.parseInt(pegarValorDigitado(menu, titulo));
                    switch (valorSelecionado) {
                        case 1: // Adicionar item
                            cadastrarItemCompra(id);
                            break;
                        case 2: // Finalizar compra
                            boolean resultado;
                            do { // Pede o valor pago que deve ser menor ou igual ao valor faltante
                                int pago = Integer.parseInt(pegarValorDigitado("Digite o valor pago:", titulo));
                                resultado = gCompras.atualizaValorFaltante(id, pago);
                                if (!resultado) {
                                    mostrarErro("Valor pago maior que o valor faltante!", titulo);
                                }
                            } while (!resultado);

                            mostrarMensagem("Compra finalizada com sucesso!", titulo);
                            mostrarMensagem(gCompras.buscarCompraPeloIdentificador(id).paraStringFormatado(), titulo);
                            break;
                    }
                } while (valorSelecionado != 2);
            } else {
                throw new Exception("Erro ao cadastrar compra");
            }
            mostrarMensagem("Compra efetuada com sucesso!", titulo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            mostrarErro("Erro ao efetuar compra", "Erro");
        }
    }

    public void cadastrarItemCompra(int identificador) {
        try {
            String titulo = "Cadastrar um produto";
            String nomeProduto = pegarValorDigitado("Digite o nome do produto:", titulo);
            int quantidade = Integer.parseInt(pegarValorDigitado("Digite a quantidade do produto:", titulo));
            double precoUnitario = Double.parseDouble(pegarValorDigitado("Digite o preço unitario:", titulo));
            gCompras.adicionarItemCompraPorIdentificador(identificador,
                    gCompras.criarItemCompra(quantidade, nomeProduto, precoUnitario, quantidade * precoUnitario));
            mostrarMensagem("Item de compra criado e adicionado com sucesso!", titulo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizarSituacaoPagamento() {
        try {

            String titulo = "Atualizar situação de pagamento";

            int identificador = Integer.parseInt(pegarValorDigitado("Digite o identificador da compra", titulo));
            double valorFaltante = gCompras.pegarValorFaltantePorIdentificador(identificador);
            String valorPagoString = pegarValorDigitado(
                    String.format("Digite o valor a pagar (restante: %.2f)", valorFaltante), titulo);

            if (identificador != 0 && valorPagoString != null) {
                try {
                    double valorPago = Double.parseDouble(valorPagoString);
                    boolean atualizacaoSucesso = gCompras.atualizaValorFaltante(identificador, valorPago);
                    if (atualizacaoSucesso) {
                        mostrarMensagem("Valor pago atualizado com sucesso!", titulo);
                    } else {
                        throw new Exception("Valor pago maior que o valor faltante!");
                    }
                } catch (NumberFormatException e) {
                    mostrarErro("Valor pago inválido: \n", "Erro");
                }
            }
        } catch (Exception e) {
            mostrarErro("Erro ao atualizar situação de pagamento: \n" + e.getMessage(), "Erro");
        }
    }

    public String menuRelatorios() {
        String menu = "(a) Relação de todos os Clientes que possuem o nome iniciado por uma determinada sequência de caracteres\n"
                +
                "(b) Relação de todos os Produtos\n" +
                "(c) Busca de um produto pelo nome\n" +
                "(d) Relação de produtos que são perecíveis e que estão com a data de validade vencida\n" +
                "(e) Relação de todas as compras\n" +
                "(f) Busca de uma compra pelo número\n" +
                "(g) Relação de todas as compras que não foram pagas ainda\n" +
                "(h) Relação das 10 últimas compras pagas\n" +
                "(i) Apresentação das informações da compra mais cara\n" +
                "(j) Apresentação das informações da compra mais barata\n" +
                "(k) Relação do valor total de compras feitas em cada mês nos últimos 12 meses\n" +
                "(l) Voltar\n";

        String valorSelecionado = JOptionPane.showInputDialog(null, menu, "Menu Principal", JOptionPane.PLAIN_MESSAGE);

        if (valorSelecionado == null) {
            return "l";
        }

        System.out.println(valorSelecionado);
        return valorSelecionado;
    }

    public void relatorioClientes() {
        String nome = pegarValorDigitado("Digite o nome do cliente:", "Relatório de Clientes");
        System.out.println(nome);
        String lista = gClientes.ListaClientesParaString(gClientes.listarClientesPorNome(nome));
        if (lista.equals("")) {
            mostrarErro("Nenhum cliente encontrado!", "Erro");
        } else {
            System.out.println(lista);
            mostrarMensagemGrande(lista, "Clientes por nome: " + nome);
        }
    }

    public void relatorioProdutos() {
        String titulo = "Relação de todos os Produtos";
        String lista = gProdutos.ListaProdutosParaString(gProdutos.listarProdutos());
        if (lista.equals("")) {
            mostrarErro("Nenhum produto encontrado!", "Erro");
        } else {
            mostrarMensagemGrande(lista, titulo);
        }
    }
    
    public void relatorioProdutosPorNome() {
    String nomeProduto = pegarValorDigitado("Digite o nome do produto:", "Relatório de Produtos");
    System.out.println(nomeProduto);
    if (nomeProduto == null) {
        mostrarAlerta("Operação Cancelada", "Aviso");
        return;
    }
    String listaProdutos = gProdutos.ListaProdutosParaString(gProdutos.listarProdutosPorNome(nomeProduto));
    if (listaProdutos.equals("")) {
        mostrarErro("Nenhum produto encontrado!", "Erro");
        } else {
        System.out.println(listaProdutos);
        mostrarMensagemGrande(listaProdutos, "Produtos por nome: " + nomeProduto);
        }
    }

public void relatorioProdutosVencidos() {
    String titulo = "Relação de Produtos Vencidos";
    String lista = gProdutos.listaProdutosVencidosParaString(gProdutos.listarProdutosVencidos());

    if (lista.equals("")) {
        mostrarErro("Nenhum produto vencido encontrado!", "Erro");
        } else {
        mostrarMensagemGrande(lista, titulo);
        }
    }

    public void compraMaisCara() {
        try {
            String titulo = "Compra mais cara";
            String compra = gCompras.buscarCompraMaisCara().paraStringFormatado();
            System.out.println(compra);
            mostrarMensagemGrande(compra, titulo);
        } catch (Exception e) {
            mostrarErro("Nenhuma compra encontrada!", "Erro");
        }
    }

    public void compraMaisBarata() {
        try {
            String titulo = "Compra mais barata";
            String compra = gCompras.buscarCompraMaisBarata().paraStringFormatado();
            System.out.println(compra);
            mostrarMensagemGrande(compra, titulo);
        } catch (Exception e) {
            mostrarErro("Nenhuma compra encontrada!", "Erro");
        }
    }

    public void valorComprasUltimos12Meses() {
        try {
            String titulo = "Valor total de compras feitas em cada mês nos últimos 12 meses";
            String compras = gCompras.valorTotalUltimos12MesesParaString(gCompras.buscarComprasUltimos12Meses());
            System.out.println(compras);
            mostrarMensagem(compras, titulo);
        } catch (Exception e) {
            mostrarErro("Nenhuma compra encontrada!", "Erro");
        }
    }
}
 
