package view;

import javax.swing.JOptionPane;

import controller.GerenciamentoClientes;

/**
 * InterfaceUsuario
 */
public class InterfaceUsuario {
    private GerenciamentoClientes gc;

    public InterfaceUsuario(GerenciamentoClientes gc) {
        this.gc = gc;
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
    public String pegaValorDigitado(String msg, String titulo) {
        String valorDigitado = JOptionPane.showInputDialog(null, msg, titulo, JOptionPane.PLAIN_MESSAGE);
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
                    String nome = pegaValorDigitado("Digite o nome do cliente:", titulo);
                    String rua = pegaValorDigitado("Digite a rua do cliente:", titulo);
                    int numero = Integer.parseInt(pegaValorDigitado("Digite o número da casa:", titulo));
                    String bairro = pegaValorDigitado("Digite o bairro do cliente:", titulo);
                    String cep = pegaValorDigitado("Digite o CEP do cliente:", titulo);
                    String cidade = pegaValorDigitado("Digite a cidade do cliente:", titulo);
                    String estado = pegaValorDigitado("Digite o estado do cliente:", titulo);

                    switch (opcaoInt) {
                        case 1: // Pessoa Física
                            String cpf = pegaValorDigitado("Digite o CPF do cliente:", titulo);
                            int qtdMaxParcelas = Integer.parseInt(pegaValorDigitado("Digite a quantidade máxima de parcelas do cliente:", titulo));

                            gc.cadastrarCliente(gc.criarPessoaFisica(
                                nome,
                                gc.criarEndereco(rua, numero, bairro, cep, cidade, estado),
                                cpf,
                                qtdMaxParcelas
                            ));

                            mostrarMensagem("Cliente cadastrado com sucesso!", titulo);
                            break;
                        case 2: // Pessoa Jurídica
                            String cnpj = pegaValorDigitado("Digite o CNPJ do cliente:", titulo);
                            String razaoSocial = pegaValorDigitado("Digite a razão social do cliente:", titulo);
                            int prazoPagamento = Integer.parseInt(pegaValorDigitado("Digite o prazo de pagamento do cliente:", titulo));
                            
                            gc.cadastrarCliente(gc.criarPessoaJuridica(
                                nome,
                                gc.criarEndereco(rua, numero, bairro, cep, cidade, estado),
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
        String documento = pegaValorDigitado("Digite o documento do cliente:", titulo);
        if (gc.deletarPeloDocumento(documento)) {
            mostrarMensagem("Cliente deletado com sucesso!", titulo);
        } else {
            mostrarErro("Cliente não encontrado!", titulo);
        }
    }

    public void deletarClientePorNome(){
        String titulo = "Deletar Cliente";
        String nome = pegaValorDigitado("Digite o nome do cliente:", titulo);
        if (gc.deletarPeloNome(nome)) {
            mostrarMensagem("Cliente deletado com sucesso!", titulo);
        } else {
            mostrarErro("Cliente não encontrado!", titulo);
        }
    }
}