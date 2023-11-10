package model;

import java.time.LocalDate;

// ! Seria interessante verificar a data atual antes de registrar uma data de validade

public class ProdutoPerecivel extends Produto {
    private LocalDate dataDeValidade;

    public ProdutoPerecivel(String nome, double preco, String descricao, LocalDate dataDeValidade){
        super(nome,preco,descricao);
        this.dataDeValidade = dataDeValidade;
    }

    public ProdutoPerecivel(int codigo, String nome, double preco, String descricao, LocalDate dataDeValidade){
        super(codigo,nome,preco,descricao);
        this.dataDeValidade = dataDeValidade;
    }

    public LocalDate pegarDataDeValidade() {
        return dataDeValidade;
    }

    public void setarDataDeValidade(LocalDate dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    public boolean estaVencido() {
        LocalDate dataAtual = LocalDate.now(); // obtém a data atual
        return dataDeValidade.isBefore(dataAtual); // compara a data de validade com a data atual
    }

    @Override
    public String paraStringFormatado() {
        return super.paraString() +
                ", Data De Validade: " + dataDeValidade;
    }

    @Override
    public String paraString() {
        return super.paraString() + "," +
                dataDeValidade;
    }
}
