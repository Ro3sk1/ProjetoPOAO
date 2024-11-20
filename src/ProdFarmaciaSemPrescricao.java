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

    public String getSubTipoProduto() {
        return "SP";
    }

    public double calcularIVA(Clientes cliente) {
        double iva = 0.23;
        if(getCategoria().equals("animais")) {
            iva -= 0.01;
        }
        return iva;
    }
}
