/**
 * Representa um produto alimentar que se enquadra na categoria de taxa de IVA intermédia.
 * É uma especialização da classe {@link ProdAlimentar} e adiciona informações específicas
 * sobre a categoria do produto.
 */
public class ProdAlimentarTaxaIntermedia extends ProdAlimentar {

    /**
     * Categoria do produto alimentar.
     */
    protected String categoria;

    /**
     * Construtor que inicializa todos os atributos do produto alimentar.
     *
     * @param codigo         Código único do produto.
     * @param nome           Nome do produto.
     * @param descricao      Descrição detalhada do produto.
     * @param quantidade     Quantidade do produto em estoque.
     * @param valor_unitario Valor unitário do produto (sem IVA).
     * @param biologico      Indica se o produto é biológico.
     * @param categoria      Categoria específica do produto (ex.: "Vinho").
     */
    public ProdAlimentarTaxaIntermedia(String codigo, String nome, String descricao, int quantidade, double valor_unitario, boolean biologico, String categoria) {
        super(codigo, nome, descricao, quantidade, valor_unitario, biologico);
        this.categoria = categoria;
    }

    /**
     * Construtor padrão sem parâmetros.
     */
    public ProdAlimentarTaxaIntermedia() {
    }

    /**
     * Obtém a categoria do produto alimentar.
     * @return A categoria do produto.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do produto alimentar.
     * @param categoria A categoria a ser atribuída ao produto.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Calcula o valor da taxa de IVA aplicável ao produto com base na localização do cliente,
     * na categoria do produto e se ele é biológico.
     * @param cliente O cliente para quem o IVA será calculado.
     * @return O valor da taxa de IVA aplicável.
     */
    public double calcularIVA(Clientes cliente) {
        double iva;
        // Constantes de IVA
        double IVA_PORTUGAL_CONTINENTAL = 0.13;
        double IVA_MADEIRA = 0.12;
        double IVA_ACORES = 0.09;
        double EXTRA_VINHO = 0.01;
        double DESCONTO_BIOLOGICO = 0.9;

        // Determina a taxa de IVA com base na localização
        switch (cliente.getLocalizacao()) {
            case "Portugal Continental" -> iva = IVA_PORTUGAL_CONTINENTAL;
            case "Madeira" -> iva = IVA_MADEIRA;
            case "Açores" -> iva = IVA_ACORES;
            default -> iva = 0;
        }

        // Aplica ajuste para a categoria "Vinho"
        if (categoria.equals("Vinho")) {
            iva += EXTRA_VINHO;
        }

        // Aplica desconto para produtos biológicos
        if (biologico) {
            iva *= DESCONTO_BIOLOGICO;
        }

        return iva;
    }

    /**
     * Exibe informações detalhadas sobre o produto, incluindo preço total,
     * IVA aplicado e quantidade.
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
     * Retorna uma representação textual do produto alimentar com taxa de IVA intermédia.
     * @return Uma string representando os detalhes do produto.
     */
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
