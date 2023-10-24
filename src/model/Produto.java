public class Produto {
    private int codigo;
    private String nome;
    private String descricao;
    private double preco;

    @Override
    public String toString(){
        return "Produto{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                '}'; 
        }

    public int getCodigo(){
        return codigo;
    }

    public void setCodigo(int codigo) {this.codigo = codigo;}

    public String getNome() {return nome;}

    public void setNome(String nome){this.nome = nome;}

    public String getDescricao() {return descricao;}

    public void SetDescricao(String descricao){this.descricao = descricao;}

    public double getPreco() {return preco;}

    public void setPreco(double preco){this.preco = preco;}
}
