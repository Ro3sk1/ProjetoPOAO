
/**
 * Representa um produto farmacêutico que não requer prescrição médica.
 * É uma especialização da classe {@link ProdFarmacia}.
 */
public class ProdFarmaciaSemPrescricao extends ProdFarmacia{

    /**
     * Categoria do produto farmacêutico.
     */
    protected String categoria;

    /**
     * Construtor que inicializa os atributos do produto farmacêutico sem prescrição.
     *
     * @param codigo          Código único do produto.
     * @param nome            Nome do produto.
     * @param descricao       Descrição detalhada do produto.
     * @param quantidade      Quantidade do produto.
     * @param valorUnitario   Valor unitário do produto (sem IVA).
     * @param categoria       Categoria específica do produto.
     */
    public ProdFarmaciaSemPrescricao(String codigo, String nome, String descricao, int quantidade, double valorUnitario, String categoria) {
        super(codigo, nome, descricao, quantidade, valorUnitario);
        this.categoria = categoria;
    }

    /**
     * Construtor padrão sem parâmetros.
     */
    public ProdFarmaciaSemPrescricao() {
    }

    /**
     * Obtém a categoria do produto farmacêutico.
     * @return a categoria do produto farmacêutico.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do produto farmacêutico.
     * @param categoria Categoria do produto farmacêutico.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Calcula o IVA (Imposto sobre o Valor Acrescentado) aplicável ao produto,
     * baseado na localização do cliente.
     * @param cliente O cliente para quem o IVA será calculado.
     * @return O valor da taxa de IVA aplicável.
     */
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

    /**
     * Exibe as informações detalhadas do produto.
     * @param cliente O cliente relacionado ao produto.
     */
    public void printProduto(Clientes cliente) {
        double precoComIva = valor_unitario * (1 + calcularIVA(cliente));
        double precoIvaTotal = valor_unitario * quantidade * calcularIVA(cliente);
        double precoTotal = precoComIva * quantidade;

        System.out.println(Cores.AZUL.getCode() + nome + Cores.RESET.getCode() + Cores.NEGRITO.getCode() + " (" + descricao + ") - " + Cores.RESET.getCode() + Cores.AMARELO.getCode() + String.format("%.2f", precoTotal) + "€ " + Cores.VERMELHO.getCode() + "(IVA: " + String.format("%.2f", precoIvaTotal) + "€ | " + String.format("%.1f", calcularIVA(cliente) * 100) + "%)" + Cores.RESET.getCode());
    }

    /**
     * Retorna uma representação textual do produto farmacêutico sem prescrição,
     * @return Uma string representando os detalhes do produto farmacêutico sem prescrição.
     */
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
