package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import model.Cliente;
import model.Endereco;
import model.PessoaFisica;
import model.PessoaJuridica;

public class GerenciamentoArquivos {
    GerenciamentoClientes gerenciamentoClientes;
    GerenciamentoProdutos gerenciamentoProdutos;
    GerenciamentoCompras gerenciamentoCompras;

    File fclientes = new File("src/baseDados/dadosClientes.txt");
    File fcompras = new File("src/baseDados/dadosCompras.txt");
    File fprodutos = new File("src/baseDados/dadosProdutos.txt");

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
        //TODO salvarCompras(), salvarProdutos();
    }

    public void lerDados(){
        lerClientes();
        //TODO lerCompras(), lerProdutos();
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

    public void lerClientes(){
        try (BufferedReader br = new BufferedReader(new FileReader(fclientes))) {
            while (br.ready()){
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

                if ("PF".equals(tipo)){
                    cliente = new PessoaFisica(nome, data, documento, Integer.parseInt(dados[4]), new Endereco(rua, numero, bairro, cep, cidade, estado));
                }
                if ("PJ".equals(tipo)){
                    cliente = new PessoaJuridica(nome, data, documento, dados[4], Integer.parseInt(dados[5]), new Endereco(rua, numero, bairro, cep, cidade, estado));
                }

                if (cliente != null){
                    gerenciamentoClientes.cadastrarCliente(cliente);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler clientes: " + e.getMessage());
        }
    }

    // TODO Ler e Salvar comprar do arquivo dadosCompras.txt
    // TODO Ler e Salvar produtos do arquivo dadosProdutos.txt
}
