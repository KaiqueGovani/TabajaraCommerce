package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Endereco;
import model.PessoaFisica;
import model.PessoaJuridica;

/**
 * GerenciamentoClientes
 */
public class GerenciamentoClientes {
    private List<Cliente> clientes;

    /**
     * Construtor
     */
    public GerenciamentoClientes() {
        this.clientes = new ArrayList<>();
    }

    /**
     * Cria um cliente
     * 
     * @param cliente
     * @return true se o cliente foi criado, false caso contrário
     */
    public boolean cadastrarCliente(Cliente cliente) {
        if (buscaPeloDocumento(cliente.pegarDocumento())) {
            return false; // Cliente já existe
        }
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
        System.out.println(cliente.paraString());
        return true;
    }

    public Endereco criarEndereco(String rua, int numero, String bairro, String cep, String cidade, String estado) {
        return new Endereco(rua, numero, bairro, cep, cidade, estado);
    }

    public PessoaFisica criarPessoaFisica(String nome, Endereco endereco, String cpf, int qtdMaxParcelas) {
        return new PessoaFisica(nome, endereco, LocalDate.now(), cpf, qtdMaxParcelas);
    }

    public PessoaJuridica criarPessoaJuridica(String nome, Endereco endereco, String cnpj, String razaoSocial, int prazoPagamento) {
        return new PessoaJuridica(nome, endereco, LocalDate.now(), cnpj, razaoSocial, prazoPagamento);
    }

    /**
     * Busca um cliente pelo documento
     * 
     * @param documento
     * @return true se o houver um cliente com o documento, false caso contrário
     */
    public boolean buscaPeloDocumento(String documento) {
        for (Cliente cliente : clientes) {
            if (cliente.pegarDocumento().equals(documento)) {
                return true;
            }
        }
        return false;
    }

    public List<Cliente> listarClientes() {
        // ! Verificar necessidade dessa função
        return new ArrayList<>(this.clientes); // Retorna uma cópia da lista
    }

    /**
     * Busca um cliente pelo documento
     * 
     * @param documento
     * @return o cliente se encontrar, null caso contrário
     */
    public List<Cliente> listarClientesPorNome(String nome) {
        List<Cliente> clientesPorNome = new ArrayList<>();
        for (Cliente cliente : clientes) {
            if (cliente.pegarNome()
                    .toLowerCase(null)
                    .startsWith(nome.toLowerCase(null))) {
                clientesPorNome.add(cliente);
            }
        }
        return clientesPorNome;
    }

    /**
     * Busca um cliente pelo documento
     * 
     * @param documento
     * @return true se encontrar e deletar o cliente, false caso contrário
     */
    public boolean deletarPeloDocumento(String documento) {
        for (Cliente cliente : clientes) {
            if (cliente.pegarDocumento().equals(documento)) {
                clientes.remove(cliente);
                return true;
            }
        }
        return false;
    }

    /**
     * Deleta um cliente pelo nome exato case insensitive
     * 
     * @param nome
     * @return true se encontrar e deletar, false caso contrario
     */
    public boolean deletarPeloNome(String nome) {
        for (Cliente cliente : clientes) {
            if (cliente.pegarNome()
                    .toLowerCase(null)
                    .equals(nome.toLowerCase(null))) {
                clientes.remove(cliente);
                return true;
            }
        }
        return false;
    }
}