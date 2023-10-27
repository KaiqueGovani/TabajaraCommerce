package view;

import javax.swing.JOptionPane;

/**
 * InterfaceUsuario
 */
public class InterfaceUsuario {

    public static void mostrarErro(String msg, String titulo) {
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public static String menuPrincipal() {
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

        return valorSelecionado;
    }

    public static void avisoSaindo() {
        JOptionPane.showMessageDialog(null, "Saindo da aplicação", "Saindo", JOptionPane.WARNING_MESSAGE);
    }

    public static void cadastrarCliente() {
        String menu = "1. Pessoa Física\n" +
                "2. Pessoa Jurídica\n" +
                "Digite o número da opção desejada:";

        String valorSelecionado = JOptionPane.showInputDialog(null, menu, "Cadastro de Clientes",
                JOptionPane.PLAIN_MESSAGE);

        String titulo = "Cadastro de Clientes";
        if (valorSelecionado != null) {
            try {
                int opcaoInt = Integer.parseInt(valorSelecionado);
                if (opcaoInt >= 1 && opcaoInt <= 2) {
                    switch (opcaoInt) {
                        case 1:
                            // TODO 1.1. Cadastro de Pessoa Física
                            String nome = pegaValorDigitado("Digite o nome do cliente:", titulo);
                            break;
                        case 2:
                            // TODO 1.2. Cadastro de Pessoa Jurídica
                            break;
                    }
                } else {
                    InterfaceUsuario.mostrarErro("Opção inválida!", "Erro");
                }
            } catch (NumberFormatException e) {
                InterfaceUsuario.mostrarErro("Entrada inválida!", "Erro");
            }
        }
    }

    public static String pegaValorDigitado(String msg, String titulo) {
        String valorDigitado = JOptionPane.showInputDialog(null, msg, titulo, JOptionPane.PLAIN_MESSAGE);
        return valorDigitado;
    }
}