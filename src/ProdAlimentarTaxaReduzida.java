import java.util.List;

/**
 * Representa um produto alimentar que se enquadra na categoria de taxa de IVA reduzida.
 * É uma especialização da classe {@link ProdAlimentar}.
 */
public class ProdAlimentarTaxaReduzida extends ProdAlimentar {

    /**
     * Lista de certificações associadas ao produto.
     */
    protected List<String> certificacoes;

    /**
     * Construtor que inicializa todos os atributos do produto alimentar.
     *
     * @param codigo         Código único do produto.
     * @param nome           Nome do produto.
     * @param descricao      Descrição detalhada do produto.
     * @param quantidade     Quantidade do produto.
     * @param valor_unitario Valor unitário do produto (sem IVA).
     * @param biologico      Indica se o produto é biológico.
     * @param certificacoes  Lista de certificações do produto.
     */
    public ProdAlimentarTaxaReduzida(String codigo, String nome, String descricao, int quantidade, double valor_unitario, boolean biologico, List<String> certificacoes) {
        super(codigo, nome, descricao, quantidade, valor_unitario, biologico);
        this.certificacoes = certificacoes;
    }

    /**
     * Construtor padrão sem parâmetros.
     */
    public ProdAlimentarTaxaReduzida() {
    }

    /**
     * Obtém a lista de certificações do produto.
     * @return A lista de certificações.
     */
    public List<String> getCertificacoes() {
        return certificacoes;
    }

    /**
     * Define a lista de certificações do produto.
     * @param certificacoes A nova lista de certificações.
     */
    public void setCertificacoes(List<String> certificacoes) {
        this.certificacoes = certificacoes;
    }

    /**
     * Calcula o valor da taxa de IVA aplicável ao produto com base na localização do cliente,
     * número de certificações e se o produto é biológico.
     * @param cliente O cliente para quem o IVA será calculado.
     * @return O valor da taxa de IVA aplicável.
     */
    public double calcularIVA(Clientes cliente) {
        double iva;
        // Constantes de IVA
        double IVA_PORTUGAL_CONTINENTAL = 0.06;
        double IVA_MADEIRA = 0.05;
        double IVA_ACORES = 0.04;
        double EXTRA_CERTIFICACOES = 0.01;
        double DESCONTO_BIOLOGICO = 0.9;

        // Determina a taxa de IVA com base na localização
        switch (cliente.getLocalizacao()) {
            case "Portugal Continental" -> iva = IVA_PORTUGAL_CONTINENTAL;
            case "Madeira" -> iva = IVA_MADEIRA;
            case "Açores" -> iva = IVA_ACORES;
            default -> iva = 0;
        }

        // Aplica desconto para certificações
        if (certificacoes.size() == 4) {
            iva -= EXTRA_CERTIFICACOES;
        }

        // Aplica desconto para produtos biológicos
        if (biologico) {
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
     * Retorna uma representação textual do produto alimentar com taxa de IVA reduzida.
     * @return Uma string representando os detalhes do produto.
     */
    @Override
    public String toString() {
        return "ProdAlimentarTaxaReduzida{" +
                "certificacoes=" + certificacoes +
                ", biologico=" + biologico +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", valor_unitario=" + valor_unitario +
                '}';
    }
}
