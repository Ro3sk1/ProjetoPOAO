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

    public double calcularIVA(Clientes cliente) {
        //constantes
        double IVA_BASE = 0.23;
        double EXTRA_ANIMAIS = 0.01;

        double iva = IVA_BASE;

        if(getCategoria().equals("animais")) {
            iva -= EXTRA_ANIMAIS;
        }
        return iva;
    }

    public void printProduto(Clientes cliente) {
        double precoComIva = valor_unitario * (1 + calcularIVA(cliente));
        double precoIvaTotal = valor_unitario * quantidade * calcularIVA(cliente);
        double precoTotal = precoComIva * quantidade;

        System.out.println(Cores.AZUL.getCode() + nome + Cores.RESET.getCode() + Cores.NEGRITO.getCode() + " (" + descricao + ") - " + Cores.RESET.getCode() + Cores.AMARELO.getCode() + String.format("%.2f", precoTotal) + "€ " + Cores.VERMELHO.getCode() + "(IVA: " + String.format("%.2f", precoIvaTotal) + "€ | " + String.format("%.1f", calcularIVA(cliente) * 100) + "%)" + Cores.RESET.getCode());
    }

    @Override
    public String toString() {
        return "ProdFarmaciaSemPrescricao{" +
                "categoria='" + categoria + '\'' +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", valor_unitario=" + valor_unitario +
                '}';
    }
}
