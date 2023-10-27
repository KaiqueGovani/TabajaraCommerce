package model;

import java.time.LocalDate;

public class ProdutoPerecivel extends Produto {
    private LocalDate dataDeValidade;

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
    public String paraString() {
        return super.paraString() +
                ", Data De Validade: " + dataDeValidade;
    }
}
