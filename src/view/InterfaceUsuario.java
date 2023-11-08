package view;

import javax.swing.JOptionPane;

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
                            gProdutos.criarProduto(nome, preco, descricao);
                            mostrarMensagem("Produto cadastrado com sucesso!", titulo);
                            break;
                        case 2: // Produto Perecível
                            String dataValidade = pegarValorDigitado("Digite a data de validade do produto (dd/mm/yyyy):", titulo);
                           
                            gProdutos.criarProdutoPerecivel(nome, preco, descricao, gProdutos.criarLocalDate(dataValidade));
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
            if (id > 0) {
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
                            int pago = Integer.parseInt(pegarValorDigitado("Digite o valor pago:", titulo));
                            gCompras.atualizaValorFaltante(id, pago);
                            mostrarMensagem("Compra finalizada com sucesso!", titulo);
                            mostrarMensagem(gCompras.buscarCompraPeloIdentificador(id).paraStringFormatado(), titulo);
                            break;
                    }
                } while (valorSelecionado != 2);
            } else {
                throw new Exception("Erro ao cadastrar compra");
            }
            mostrarMensagem("Compra por CPF efetuada com sucesso!", titulo);
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
        String titulo = "Atualizar situação de pagamento";

        // ! Use a função pegarValorDigitado para pegar o valor digitado pelo usuário
        // ! Nessas 2 linhas abaixo!

        // ! Identificador é um Inteiro em vez de string
        String identificador = JOptionPane.showInputDialog(null, "Digite o identificador da compra:",
                "Atualizar Situação de Pagamento", JOptionPane.PLAIN_MESSAGE);
        String valorPagoString = JOptionPane.showInputDialog(null, "Digite o valor pago:",
                "Atualizar Situação de Pagamento", JOptionPane.PLAIN_MESSAGE);

        if (identificador != null && valorPagoString != null) {
            try {
                double valorPago = Double.parseDouble(valorPagoString);
                boolean atualizacaoSucesso = gCompras.atualizaValorFaltante(0, valorPago);
                if (atualizacaoSucesso) {
                    mostrarMensagem("Valor pago atualizado com sucesso!", titulo);
                } else {
                    mostrarErro("Falha ao atualizar o valor pago. Verifique o identificador e tente novamente.",
                            "Erro");
                }
            } catch (NumberFormatException e) {
                mostrarErro("Valor pago inválido!", "Erro");
            }
        }
    }

}
