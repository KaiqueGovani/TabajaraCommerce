package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Compra;
import model.Endereco;
import model.ItemCompra;
import model.PessoaFisica;
import model.PessoaJuridica;
import model.Produto;
import model.ProdutoPerecivel;

public class GerenciamentoArquivos {
    GerenciamentoClientes gerenciamentoClientes;
    GerenciamentoProdutos gerenciamentoProdutos;
    GerenciamentoCompras gerenciamentoCompras;

    File fclientes = new File("src/baseDados/dadosClientes.csv");
    File fcompras = new File("src/baseDados/dadosCompras.csv");
    File fprodutos = new File("src/baseDados/dadosProdutos.csv");

    public GerenciamentoArquivos(GerenciamentoClientes gerenciamentoClientes,
            GerenciamentoProdutos gerenciamentoProdutos,
            GerenciamentoCompras gerenciamentoCompras) {
        this.gerenciamentoClientes = gerenciamentoClientes;
        this.gerenciamentoProdutos = gerenciamentoProdutos;
        this.gerenciamentoCompras = gerenciamentoCompras;

        setupArquivos();
    }

    public void setupArquivos() {
        try {
            // Cria os arquivos caso não existam
            fclientes.createNewFile();
            fcompras.createNewFile();
            fprodutos.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar arquivos de dados: " + e.getMessage());
        }
    }

    public void salvarDados() {
        salvarClientes(gerenciamentoClientes.listarClientes());
        salvarCompras(gerenciamentoCompras.listarCompras());
        salvarProdutos(gerenciamentoProdutos.listarProdutos());
    }

    public void lerDados() {
        lerClientes();
        lerCompras();
        lerProdutos();
    }

    public void salvarClientes(List<Cliente> clientes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fclientes))) {
            for (Cliente cliente : clientes) {
                bw.write(cliente.paraString()); // escreve o cliente no arquivo
                bw.newLine(); // adiciona um \n no final
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    public void lerClientes() {
        try (BufferedReader br = new BufferedReader(new FileReader(fclientes, StandardCharsets.UTF_8))) {
            while (br.ready()) {
                String linha = br.readLine();
                String[] dados = linha.split(",");

                // Criar cliente a partir dos dados
                Cliente cliente = null;
                String tipo = dados[0];
                String nome = dados[1];
                LocalDate data = LocalDate.parse(dados[2]);
                String documento = dados[3];
                String rua = dados[dados.length - 6];
                int numero = Integer.parseInt(dados[dados.length - 5]);
                String bairro = dados[dados.length - 4];
                String cep = dados[dados.length - 3];
                String cidade = dados[dados.length - 2];
                String estado = dados[dados.length - 1];

                if ("PF".equals(tipo)) {
                    cliente = new PessoaFisica(nome, data, documento, Integer.parseInt(dados[4]),
                            new Endereco(rua, numero, bairro, cep, cidade, estado));
                }
                if ("PJ".equals(tipo)) {
                    cliente = new PessoaJuridica(nome, data, documento, dados[4], Integer.parseInt(dados[5]),
                            new Endereco(rua, numero, bairro, cep, cidade, estado));
                }

                if (cliente != null) {
                    gerenciamentoClientes.cadastrarCliente(cliente);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler clientes: " + e.getMessage());
        }
    }

    public void salvarProdutos(List<Produto> produtos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fprodutos))) {
            for (Produto produto : produtos) {
                bw.write(produto.paraString()); // escreve o produto no arquivo
                bw.newLine(); // adiciona um \n no final
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    public void lerProdutos() {
        try (BufferedReader br = new BufferedReader(new FileReader(fprodutos, StandardCharsets.UTF_8))) {
            while (br.ready()) {
                String linha = br.readLine();
                String[] dados = linha.split(",");

                // Verifica se o produto é perecível ou não
                if (dados.length == 4) { // Produto não perecível
                    int codigo = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    String descricao = dados[2];
                    double preco = Double.parseDouble(dados[3]);
                    
                    Produto produto = new Produto(codigo, nome, preco, descricao);

                    if (produto != null) {
                        gerenciamentoProdutos.cadastrarProdutos(produto);
                    }
                } else if (dados.length == 5) { // Produto perecível
                    int codigo = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    String descricao = dados[2];
                    double preco = Double.parseDouble(dados[3]);
                    LocalDate dataValidade = LocalDate.parse(dados[4]);

                    ProdutoPerecivel produto = new ProdutoPerecivel(codigo, nome, preco, descricao, dataValidade);

                    if (produto != null) {
                        gerenciamentoProdutos.cadastrarProdutos(produto);
                    }
                } else {
                    throw new RuntimeException("Formato de dados inválido.");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler produtos: " + e.getMessage());
        }
    }

    public void salvarCompras(List<Compra> compras) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fcompras))) {
            for (Compra compra : compras) {
                bw.write(compra.paraString()); // escreve a compra no arquivo
                bw.newLine(); // adiciona um \n no final
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar compra: " + e.getMessage());
        }
    }

    public void lerCompras() {
        try (BufferedReader br = new BufferedReader(new FileReader(fcompras, StandardCharsets.UTF_8))) {
            while (br.ready()) {
                String linha = br.readLine();
                String[] dados = linha.split(",");

                // Criar compra a partir dos dados
                int identificador = Integer.parseInt(dados[0]);
                LocalDate data = LocalDate.parse(dados[1]);
                String docCliente = dados[2];
                double valorTotal = Double.parseDouble(dados[3]);
                double totalPago = Double.parseDouble(dados[4]);
                List<ItemCompra> itensComprados = new ArrayList<>();

                // Adicionar itens comprados à lista
                for (int i = 5; i < dados.length; i += 3) {
                    String nomeProduto = dados[i];
                    int quantidade = Integer.parseInt(dados[i + 1]);
                    double precoUnitario = Double.parseDouble(dados[i + 2]);
                    itensComprados.add(new ItemCompra(quantidade, nomeProduto, precoUnitario, quantidade * precoUnitario));
                }

                Compra compra = new Compra(identificador, data, docCliente, itensComprados, valorTotal, totalPago);

                if (compra != null) {
                    gerenciamentoCompras.criarCompraExistente(compra);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler compras: " + e.getMessage());
        }
    }
}
