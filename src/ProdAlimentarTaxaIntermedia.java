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

    public String getSubTipoProduto() {
        return "TI";
    }

    public double calcularIVA(Clientes cliente) {
        double iva = 0;
        switch (cliente.getLocalizacao()) {
            case "Portugal Continental" -> iva = 0.13;
            case "Madeira" -> iva = 0.12;
            case "Açores" -> iva = 0.09;
        }
        if (getCategoria().equals("vinho")) {
            iva += 0.01;
        }
        if(isBiologico()) {
            iva = iva*0.9;
        }
        return iva;
    }
}
