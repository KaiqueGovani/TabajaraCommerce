import controller.GerenciamentoArquivos;
import controller.GerenciamentoClientes;
import controller.GerenciamentoCompras;
import controller.GerenciamentoProdutos;
import view.InterfaceUsuario;

/**
 * main
 */
public class Main {
    static GerenciamentoArquivos gerenciamentoArquivos;
    static GerenciamentoClientes gerenciamentoClientes;
    static GerenciamentoProdutos gerenciamentoProdutos;
    static GerenciamentoCompras gerenciamentoCompras;
    static InterfaceUsuario menu;

    public void setup() {
        gerenciamentoClientes = new GerenciamentoClientes();
        gerenciamentoProdutos = new GerenciamentoProdutos();
        gerenciamentoCompras = new GerenciamentoCompras();
        gerenciamentoArquivos = new GerenciamentoArquivos(gerenciamentoClientes, gerenciamentoProdutos,
                gerenciamentoCompras);
        gerenciamentoArquivos.lerDados();
        menu = new InterfaceUsuario(gerenciamentoClientes, gerenciamentoCompras, gerenciamentoProdutos);
    }

    public static void main(String[] args) {
        try {
            new Main().setup();

            int opcaoInt = -1;

            do {
                String opcao = menu.menuPrincipal();

                try {
                    opcaoInt = Integer.parseInt(opcao);
                    if (opcaoInt >= 1 && opcaoInt <= 8) {

                        switch (opcaoInt) {
                            case 1:
                                menu.cadastrarCliente();
                                break;
                            case 2:
                                menu.deletarClientePorDocumento();
                                break;
                            case 3:
                                menu.deletarClientePorNome();
                                break;
                            case 4:
                                menu.cadastrarProduto();
                                break;
                            case 5:
                                menu.efetuarCompra();
                                break;
                            case 6:
                                menu.atualizarSituacaoPagamento();
                                break;
                            case 7:
                                do {
                                    opcao = menu.menuRelatorios();
                                    if (opcao.matches("[a-l]")) {
                                        switch (opcao) {
                                            case "a":
                                                menu.relatorioClientes();
                                                break;
                                            case "b":
                                                 menu.relatorioProdutos();
                                                break;
                                            case "c":
                                                menu.relatorioProdutosPorNome();
                                                break;
                                            case "d":
                                                // TODO: Mostrar Produtos vencidos - Jota
                                                break;
                                            case "e":
                                                menu.relatorioCompras();
                                                break;
                                            case "f":
                                                menu.relatorioCompraPeloIdentificador();
                                                break;
                                            case "g":
                                                menu.relatorioComprasNaoPagas();
                                                break;
                                            case "h":
                                                // TODO: Mostrar as 10 ultimas compras pagas - Milton/Kapo
                                                menu.relatorioDezUltimasPagas();
                                                break;
                                            case "i":
                                                menu.compraMaisCara();
                                                break;
                                            case "j":
                                                menu.compraMaisBarata();
                                                break;
                                            case "k":
                                                // TODO: compras feitas em cada mes ultimos 12 meses - Kapo
                                                menu.valorComprasUltimos12Meses();
                                                break;
                                            case "l":
                                                break;
                                        }
                                    } else {
                                        InterfaceUsuario.mostrarErro("Opção inválida!", "Erro");
                                    }
                                } while (!opcao.equals("l"));
                                break;
                            case 8:
                                gerenciamentoArquivos.salvarDados();
                                InterfaceUsuario.avisoSaindo();
                                break;
                        }
                    } else {
                        InterfaceUsuario.mostrarErro("Opção inválida!", "Erro");
                    }
                } catch (NumberFormatException e) {
                    InterfaceUsuario.mostrarErro("Entrada inválida!", "Erro");
                } catch (Exception e) {
                    InterfaceUsuario.mostrarErro(e.getMessage(), "Erro");
                }

            } while (opcaoInt != 8);

        } catch (Exception e) {
            InterfaceUsuario.mostrarErro(e.getMessage(), "Erro");
        }
    }

}
