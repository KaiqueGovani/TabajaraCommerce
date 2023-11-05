package view;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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

    public InterfaceUsuario(GerenciamentoClientes gClientes, GerenciamentoCompras gCompras, GerenciamentoProdutos gProdutos) {
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
            if (valorDigitado == null || valorDigitado.equals("")) {
                mostrarAlerta("Entrada inválida!", titulo);
            }
        } while (valorDigitado == null || valorDigitado.equals(""));
        
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

        String valorSelecionado = JOptionPane.showInputDialog(null, menu, "Cadastro de Clientes", JOptionPane.PLAIN_MESSAGE);

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
                            int qtdMaxParcelas = Integer.parseInt(pegarValorDigitado("Digite a quantidade máxima de parcelas do cliente:", titulo));

                            gClientes.cadastrarCliente(gClientes.criarPessoaFisica(
                                nome,
                                gClientes.criarEndereco(rua, numero, bairro, cep, cidade, estado),
                                cpf,
                                qtdMaxParcelas
                            ));

                            mostrarMensagem("Cliente cadastrado com sucesso!", titulo);
                            break;
                        case 2: // Pessoa Jurídica
                            String cnpj = pegarValorDigitado("Digite o CNPJ do cliente:", titulo);
                            String razaoSocial = pegarValorDigitado("Digite a razão social do cliente:", titulo);
                            int prazoPagamento = Integer.parseInt(pegarValorDigitado("Digite o prazo de pagamento do cliente:", titulo));
                            
                            gClientes.cadastrarCliente(gClientes.criarPessoaJuridica(
                                nome,
                                gClientes.criarEndereco(rua, numero, bairro, cep, cidade, estado),
                                cnpj,
                                razaoSocial,
                                prazoPagamento
                            ));

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

    public void deletarClientePorNome(){
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

            String valorSelecionado = JOptionPane.showInputDialog(null, menu, "Cadastro de Produtos", JOptionPane.PLAIN_MESSAGE);

            String titulo = "Cadastro de Produto";
            if (valorSelecionado != null) {
                try {
                    int opcaoInt = Integer.parseInt(valorSelecionado);
                    if (opcaoInt >= 1 && opcaoInt <= 2) {
                        String nome = pegarValorDigitado("Digite o nome do produto:", titulo);
                        double preco = Double.parseDouble(pegarValorDigitado("Digite o preço do produto:", titulo));
                        int quantidade = Integer.parseInt(pegarValorDigitado("Digite a quantidade em estoque do produto:", titulo));

                        switch (opcaoInt) {
                            case 1: // Produto
                                gProdutos.cadastrarProduto(gProdutos.criarProduto(nome, preco, quantidade));
                                mostrarMensagem("Produto cadastrado com sucesso!", titulo);
                                break;
                            case 2: // Produto Perecível
                                String dataValidade = pegarValorDigitado("Digite a data de validade do produto (dd/mm/yyyy):", titulo);
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                Date date = sdf.parse(datadValidade);
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(date);

                                gProdutos.cadastrarProduto(gProdutos.criarProdutoPerecivel(nome, preco, quantidade, cal));
                                mostrarMensagem("Produto perecível cadastrado com sucesso!", titulo);
                                break;
                        default:
                                break;
                    }
                } else {
                    mostrarErro("Opção inválida!", "Erro");
                }
            } catch (NumberFormatException | ParseException e) {
                mostrarErro("Entrada inválida!", "Erro");
            }
        }
    }

    public void efetuarCompra() {
        String menu = "Selecione o tipo de compra:\n" +
                "1. Compra por CPF\n" +
                "2. Compra por CNPJ\n" +
                "Digite o número da opção desesejada:";

        String valorSelecionado = JOptionPane.showInputDialog(null, menu, "Efetuação de uma compra", JOptionPane.PLAIN_MESSAGE);

        String titulo = "Efetuar uma Compra";
        if (valorSelecionado != null) {
            try {
                int opcaoInt = Integer.parseInt(valorSelecionado);
                if (opcaoInt >=1 && opcaoInt <=2) {
                    int quantidade = pegarValorDigitado("Digite a quantidade do produto:", titulo);
                    String nomeProduto = pegarValorDigitado("Digite o nome do produto:", titulo);
                    double precoUnitario = pegarValorDigitado("Digite o preço unitario:", titulo);
                    int identificador = pegarValorDigitado("Digite o número do pedido", titulo);

                    switch (opcaoInt) {
                        case 1: //Compra por CPF
                        String cpf = pegarValorDigitado("Digite o CPF do cliente:", titulo);
                        int qtdParcelas = Integer.parseInt(pegarValorDigitado("Digite a quantidade de parcelas da compra:", titulo));

                            gCompras.efetuarCompra(gCompras.criarItemCompra(quantidade, nomeProduto, precoUnitario, pegarValorTotal(
                                identificador,
                                gCompras.criarCompra(valorTotal, docCliente, itensComprados, totalPago),
                                cpf,
                                qtdParcelas
                            ));

                            mostrarMensagem("Compra por CPF efetuada com sucesso!", titulo);
                            break;

                        case 2: //Compra por CNPJ
                        String cnpj = pegarValorDigitado("Digite o CNPJ do cliente:", titulo);
                        String razaoSocial = pegarValorDigitado("Digite a razão social do cliente:", titulo);
                        int prazodePagamento = Integer.parseInt(pegarValorDigitado("Digite o prazo de pagamento da compra:", titulo));

                            gCompras.efetuarCompra(gCompras.criarItemCompra(quantidade, nomeProduto, precoUnitario, pegarValorTotal(
                                identificador,
                                gCompras.criarCompra(valorTotal, docCliente, itensComprados, totalPago),
                                cnpj,
                                prazodePagamento
                            ));

                            mostrarMensagem("Compra por CNPJ efetuada com sucesso!", titulo);
                        default:
                            break;
                    }
                } else {
                    mostrarErro("Opção inválida!", "Erro");
                }
            } catch (NumberFormatException e) {
                mostrarErro("Entrada inválida", "Erro");
            }
        }
    }

    public void atualizarSituacaoPagamento() {
        String identificador = JOptionPane.showInputDialog(null, "Digite o identificador da compra:", "Atualizar Situação de Pagamento", JOptionPane.PLAIN_MESSAGE);
        String valorPagoString = JOptionPane.showInputDialog(null, "Digite o valor pago:", "Atualizar Situação de Pagamento", JOptionPane.PLAIN_MESSAGE);

        String titulo = "Atualizar situação de pagamento";
        if (identificador != null && valorPagoString != null) {
            try {
                double valorPago = Double.parseDouble(valorPagoString);
                boolean atualizacaoSucesso = gCompras.atualizaValorFaltante(0, valorPago);
                if (atualizacaoSucesso) {
                    mostrarMensagem("Valor pago atualizado com sucesso!", titulo);
                } else {
                    mostrarErro("Falha ao atualizar o valor pago. Verifique o identificador e tente novamente.", "Erro");
                }
            } catch (NumberFormatException e) {
                mostrarErro("Valor pago inválido!", "Erro");
            }
        }
    }

}
