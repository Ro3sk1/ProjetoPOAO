public class ProdAlimentarTaxaNormal extends ProdAlimentar{

    public ProdAlimentarTaxaNormal(String codigo, String nome, String descricao, int quantidade, double valor_unitario, boolean biologico) {
        super(codigo, nome, descricao, quantidade, valor_unitario, biologico);
    }

    public ProdAlimentarTaxaNormal() {
    }

    public double calcularIVA(Clientes cliente) {
        return switch (cliente.getLocalizacao()) {
            case "Portugal Continental" -> 0.23;
            case "Madeira" -> 0.22;
            case "Açores" -> 0.16;
            default -> 0;
        };
    }
}
