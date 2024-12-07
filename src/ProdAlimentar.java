public abstract class ProdAlimentar extends Produtos {

    protected boolean biologico;

    public ProdAlimentar(String codigo, String nome, String descricao, int quantidade, double valor_unitario, boolean biologico) {
        super(codigo, nome, descricao, quantidade, valor_unitario);
        this.biologico = biologico;
    }

    public ProdAlimentar() {
    }

    public boolean isBiologico() {
        return biologico;
    }

    public void setBiologico(boolean biologico) {
        this.biologico = biologico;
    }

    public abstract double calcularIVA(Clientes cliente);

    public abstract void printProduto(Clientes cliente);

    @Override
    public String toString() {
        return "ProdAlimentar{" +
                "biologico=" + biologico +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", valor_unitario=" + valor_unitario +
                '}';
    }
}
