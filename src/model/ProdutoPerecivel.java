public class ProdutoPerecivel extends Produto {
    private LocalDate dataDeValidade;

    public LocalDate getDataDeValidade(){
        return dataDeValidade;
    }

    public void setDataDeValidade(LocalDate dataDeValidade){
        this.dataDeValidade = dataDeValidade;
    }

    public boolean estaVencido(){
        LocalDate dataAtual = LocalDate.now(); //obtém a data atual
        return dataDeValidade.isBefore(dataAtual); //compara a data de validade com a data atual
    }

    @Override
    public String toString(){
        return super.toString() + ", dataDeValidade=" + dataDeValidade;
    }
}
