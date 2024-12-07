public abstract class ProdFarmacia extends Produtos {

    public ProdFarmacia(String codigo, String nome, String descricao, int quantidade, double valorUnitario) {
        super(codigo, nome, descricao, quantidade, valorUnitario);
    }

    public ProdFarmacia() {
    }

    public abstract double calcularIVA(Clientes cliente);

    public abstract void printProduto(Clientes cliente);

    @Override
    public String toString() {
        return "ProdFarmacia{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", valor_unitario=" + valor_unitario +
                '}';
    }
}