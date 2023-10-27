import view.InterfaceUsuario;

/**
 * main
 */
public class Main {

    public static void main(String[] args) {
        int opcaoInt = -1;

        do {
            String opcao = InterfaceUsuario.menuPrincipal();

            if (opcao != null) {
                try {
                    opcaoInt = Integer.parseInt(opcao);
                    if (opcaoInt >= 1 && opcaoInt <= 8) {

                        switch (opcaoInt) {
                            case 1:
                                // TODO 1. Cadastros de Clientes
                                InterfaceUsuario.cadastrarCliente();
                                break;
                            case 2:
                                // TODO 2. Deletar cliente pelo CPF ou CNPJ
                                break;
                            case 3:
                                // TODO 3. Deletar cliente pelo nome
                                break;
                            case 4:
                                // TODO 4. Cadastro de Produtos
                                break;
                            case 5:
                                // TODO 5. Efetuação de uma compra
                                break;
                            case 6:
                                // TODO 6. Atualização da situação de pagamento de uma compra
                                break;
                            case 7:
                                // TODO 7. Relatórios
                                break;
                            case 8:
                                InterfaceUsuario.avisoSaindo();
                                break;
                        }

                    } else {
                        InterfaceUsuario.mostrarErro("Opção inválida!", "Erro");
                    }
                } catch (NumberFormatException e) {
                    InterfaceUsuario.mostrarErro("Entrada inválida!", "Erro");
                }
            } else {
                InterfaceUsuario.avisoSaindo();
            }

        } while (opcaoInt != 8);

    }
}
