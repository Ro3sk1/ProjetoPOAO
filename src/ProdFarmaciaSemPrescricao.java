public class ProdFarmaciaSemPrescricao extends ProdFarmacia{

    private String VERMELHO = "\033[0;31m";
    private String VERDE = "\033[0;32m";
    private String AMARELO = "\033[0;33m";
    private String AZUL = "\033[0;34m";
    private String MAGENTA = "\033[0;35m";
    private String NEGRITO = "\033[1m";
    private String RESET = "\033[0m";

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
        double iva = 0.23;
        if(getCategoria().equals("animais")) {
            iva -= 0.01;
        }
        return iva;
    }

    public void printProduto(Clientes cliente) {
        double precoComIva = valor_unitario * (1 + calcularIVA(cliente));
        double precoIvaTotal = valor_unitario * quantidade * calcularIVA(cliente);
        double precoTotal = precoComIva * quantidade;

        System.out.println(AZUL + nome + RESET + NEGRITO + " (" + descricao + ") - " + RESET + AMARELO + String.format("%.2f", precoTotal) + "€ " + VERMELHO + "(IVA: " + String.format("%.2f", precoIvaTotal) + "€ | " + String.format("%.1f", calcularIVA(cliente) * 100) + "%)" + RESET);
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
