public class ProdAlimentarTaxaIntermedia extends ProdAlimentar{

    protected String categoria;

    public ProdAlimentarTaxaIntermedia(String codigo, String nome, String descricao, int quantidade, double valor_unitario, boolean biologico, String categoria) {
        super(codigo, nome, descricao, quantidade, valor_unitario, biologico);
        this.categoria = categoria;
    }

    public ProdAlimentarTaxaIntermedia() {
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
