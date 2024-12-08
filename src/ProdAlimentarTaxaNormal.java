/**
 * Representa um produto alimentar que se enquadra na categoria de taxa de IVA normal.
 * É uma especialização da classe {@link ProdAlimentar}.
 */
public class ProdAlimentarTaxaNormal extends ProdAlimentar {

    /**
     * Construtor que inicializa todos os atributos do produto alimentar.
     *
     * @param codigo         Código único do produto.
     * @param nome           Nome do produto.
     * @param descricao      Descrição detalhada do produto.
     * @param quantidade     Quantidade do produto.
     * @param valor_unitario Valor unitário do produto (sem IVA).
     * @param biologico      Indica se o produto é biológico.
     */
    public ProdAlimentarTaxaNormal(String codigo, String nome, String descricao, int quantidade, double valor_unitario, boolean biologico) {
        super(codigo, nome, descricao, quantidade, valor_unitario, biologico);
    }

    /**
     * Construtor padrão sem parâmetros.
     */
    public ProdAlimentarTaxaNormal() {
    }

    /**
     * Calcula o valor da taxa de IVA aplicável ao produto com base na localização do cliente
     * e se o produto é biológico.
     * @param cliente O cliente para quem o IVA será calculado.
     * @return O valor da taxa de IVA aplicável.
     */
    public double calcularIVA(Clientes cliente) {
        double iva;
        // Constantes de IVA
        double IVA_PORTUGAL_CONTINENTAL = 0.23;
        double IVA_MADEIRA = 0.22;
        double IVA_ACORES = 0.16;
        double DESCONTO_BIOLOGICO = 0.9;

        // Determina a taxa de IVA com base na localização
        switch (cliente.getLocalizacao()) {
            case "Portugal Continental" -> iva = IVA_PORTUGAL_CONTINENTAL;
            case "Madeira" -> iva = IVA_MADEIRA;
            case "Açores" -> iva = IVA_ACORES;
            default -> iva = 0;
        }

        // Aplica desconto para produtos biológicos
        if (isBiologico()) {
            iva *= DESCONTO_BIOLOGICO;
        }

        return iva;
    }

    /**
     * Exibe informações detalhadas sobre o produto.
     * @param cliente O cliente relacionado ao produto.
     */
    public void printProduto(Clientes cliente) {
        double precoComIva = valor_unitario * (1 + calcularIVA(cliente));
        double precoIvaTotal = valor_unitario * quantidade * calcularIVA(cliente);
        double precoTotal = precoComIva * quantidade;

        System.out.println(Cores.AZUL.getCode() + nome + Cores.RESET.getCode() +
                Cores.NEGRITO.getCode() + " (" + descricao + ") - " + Cores.RESET.getCode() +
                Cores.AMARELO.getCode() + String.format("%.2f", precoTotal) + "€ " +
                Cores.VERMELHO.getCode() + "(IVA: " + String.format("%.2f", precoIvaTotal) + "€ | " +
                String.format("%.1f", calcularIVA(cliente) * 100) + "%)" + Cores.RESET.getCode());
    }

    /**
     * Retorna uma representação textual do produto alimentar com taxa de IVA normal.
     * @return Uma string representando os detalhes do produto.
     */
    @Override
    public String toString() {
        return "ProdAlimentarTaxaNormal{" +
                "biologico=" + biologico +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", valor_unitario=" + valor_unitario +
                '}';
    }
}
