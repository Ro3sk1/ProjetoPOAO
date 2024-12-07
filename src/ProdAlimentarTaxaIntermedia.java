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

    public double calcularIVA(Clientes cliente) {
        double iva;
        //constantes
        double IVA_PORTUGAL_CONTINENTAL = 0.13;
        double IVA_MADEIRA = 0.12;
        double IVA_ACORES = 0.09;
        double EXTRA_VINHO = 0.01;
        double DESCONTO_BIOLOGICO = 0.9;

        switch (cliente.getLocalizacao()) {
            case "Portugal Continental" -> iva = IVA_PORTUGAL_CONTINENTAL;
            case "Madeira" -> iva = IVA_MADEIRA;
            case "Açores" -> iva = IVA_ACORES;
            default -> iva = 0;
        }
        if (categoria.equals("Vinho")) {
            iva += EXTRA_VINHO;
        }
        if(biologico) {
            iva = iva* DESCONTO_BIOLOGICO;
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
        return "ProdAlimentarTaxaIntermedia{" +
                "categoria='" + categoria + '\'' +
                ", biologico=" + biologico +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", valor_unitario=" + valor_unitario +
                '}';
    }
}
