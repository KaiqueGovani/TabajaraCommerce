package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.Cliente;

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

    // TODO Ler e Salvar clientes do arquivo dadosClientes.txt

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

    // TODO Ler e Salvar comprar do arquivo dadosCompras.txt
    // TODO Ler e Salvar produtos do arquivo dadosProdutos.txt
}
