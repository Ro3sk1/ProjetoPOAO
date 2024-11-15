public class ProdFarmaciaSemPrescricao extends ProdFarmacia{

    protected String categoria;

    public ProdFarmaciaSemPrescricao(String codigo, String nome, String descricao, int quantidade, double valorUnitario, String categoria) {
        super(codigo, nome, descricao, quantidade, valorUnitario);
        this.categoria = categoria;
    }

    public ProdFarmaciaSemPrescricao() {
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
