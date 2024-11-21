public class ProdAlimentarTaxaNormal extends ProdAlimentar{

    public ProdAlimentarTaxaNormal(String codigo, String nome, String descricao, int quantidade, double valor_unitario, boolean biologico) {
        super(codigo, nome, descricao, quantidade, valor_unitario, biologico);
    }

    public ProdAlimentarTaxaNormal() {
    }
    public String getSubTipoProduto() {
        return "TN";
    }

    public double calcularIVA(Clientes cliente) {
        double iva = 0;
        switch (cliente.getLocalizacao()) {
            case "Portugal Continental" -> iva = 0.23;
            case "Madeira" -> iva = 0.22;
            case "Açores" -> iva = 0.16;
            default -> iva = 0;
        }
        if(isBiologico()) {
            iva = iva*0.9;
        }
        return iva;
    }
}
