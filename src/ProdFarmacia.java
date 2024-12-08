/**
 * Representa um produto da farmácia. Esta é uma classe abstrata que define os métodos e atributos básicos
 * que todos os produtos farmacêuticos devem ter.
 * É uma especialização da classe {@link Produtos}.
 */
public abstract class ProdFarmacia extends Produtos {

    /**
     * Construtor que inicializa os atributos do produto de farmácia.
     *
     * @param codigo         Código único do produto.
     * @param nome           Nome do produto.
     * @param descricao      Descrição detalhada do produto.
     * @param quantidade     Quantidade do produto.
     * @param valorUnitario  Valor unitário do produto (sem IVA).
     */
    public ProdFarmacia(String codigo, String nome, String descricao, int quantidade, double valorUnitario) {
        super(codigo, nome, descricao, quantidade, valorUnitario);
    }

    /**
     * Construtor padrão sem parâmetros.
     */
    public ProdFarmacia() {
    }

    /**
     * Método abstrato para calcular o IVA (Imposto sobre o Valor Acrescentado) aplicável ao produto.
     * @param cliente O cliente para quem o IVA será calculado.
     * @return O valor da taxa de IVA aplicável.
     */
    public abstract double calcularIVA(Clientes cliente);

    /**
     * Método abstrato para exibir as informações detalhadas do produto.
     * @param cliente O cliente relacionado ao produto.
     */
    public abstract void printProduto(Clientes cliente);

    /**
     * Retorna uma representação textual do produto de farmácia.
     * @return Uma string representando os detalhes do produto de farmácia.
     */
    @Override
    public String toString() {
        return "ProdFarmacia{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", valor_unitario=" + valor_unitario +
                '}';
    }
}
