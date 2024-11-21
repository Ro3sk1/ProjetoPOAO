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

    public String getTipoProduto() {
        return "AL";
    }
}
