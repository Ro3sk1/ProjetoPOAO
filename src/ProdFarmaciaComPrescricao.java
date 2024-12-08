/**
 * Representa um produto farmacêutico que requer prescrição médica.
 * É uma especialização da classe {@link ProdFarmacia}.
 */
public class ProdFarmaciaComPrescricao extends ProdFarmacia {

    /**
     * Nome do médico que emitiu a prescrição do produto.
     */
    protected String medicoPrescritor;

    /**
     * Construtor que inicializa os atributos do produto farmacêutico com prescrição.
     *
     * @param codigo          Código único do produto.
     * @param nome            Nome do produto.
     * @param descricao       Descrição detalhada do produto.
     * @param quantidade      Quantidade do produto.
     * @param valorUnitario   Valor unitário do produto (sem IVA).
     * @param medicoPrescritor Nome do médico que emitiu a prescrição.
     */
    public ProdFarmaciaComPrescricao(String codigo, String nome, String descricao, int quantidade, double valorUnitario, String medicoPrescritor) {
        super(codigo, nome, descricao, quantidade, valorUnitario);
        this.medicoPrescritor = medicoPrescritor;
    }

    /**
     * Construtor padrão sem parâmetros.
     */
    public ProdFarmaciaComPrescricao() {
    }

    /**
     * Obtém o nome do médico que emitiu a prescrição do produto.
     * @return Nome do médico prescritor.
     */
    public String getMedicoPrescritor() {
        return medicoPrescritor;
    }

    /**
     * Define o nome do médico que emitiu a prescrição do produto.
     * @param medicoPrescritor Nome do médico prescritor.
     */
    public void setMedicoPrescritor(String medicoPrescritor) {
        this.medicoPrescritor = medicoPrescritor;
    }

    /**
     * Calcula o IVA (Imposto sobre o Valor Acrescentado) aplicável ao produto,
     * baseado na localização do cliente.
     * @param cliente O cliente para quem o IVA será calculado.
     * @return O valor da taxa de IVA aplicável.
     */
    public double calcularIVA(Clientes cliente) {
        double IVA_PORTUGAL_CONTINENTAL = 0.06;
        double IVA_MADEIRA = 0.05;
        double IVA_ACORES = 0.04;

        return switch (cliente.getLocalizacao()) {
            case "Portugal Continental" -> IVA_PORTUGAL_CONTINENTAL;
            case "Madeira" -> IVA_MADEIRA;
            case "Açores" -> IVA_ACORES;
            default -> 0;
        };
    }

    /**
     * Exibe as informações detalhadas do produto.
     * @param cliente O cliente relacionado ao produto.
     */
    public void printProduto(Clientes cliente) {
        double precoComIva = valor_unitario * (1 + calcularIVA(cliente));
        double precoIvaTotal = valor_unitario * quantidade * calcularIVA(cliente);
        double precoTotal = precoComIva * quantidade;

        System.out.println(Cores.AZUL.getCode() + nome + Cores.RESET.getCode() +
                Cores.NEGRITO.getCode() + " (" + descricao + ") - " +
                Cores.RESET.getCode() +
                Cores.AMARELO.getCode() +
                String.format("%.2f", precoTotal) + "€ " +
                Cores.VERMELHO.getCode() +
                "(IVA: " + String.format("%.2f", precoIvaTotal) + "€ | " +
                String.format("%.1f", calcularIVA(cliente) * 100) + "%)" +
                Cores.RESET.getCode());
    }

    /**
     * Retorna uma representação textual do produto farmacêutico com prescrição,
     * @return Uma string representando os detalhes do produto farmacêutico com prescrição.
     */
    @Override
    public String toString() {
        return "ProdFarmaciaComPrescricao{" +
                "medicoPrescritor='" + medicoPrescritor + '\'' +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", valor_unitario=" + valor_unitario +
                '}';
    }
}
