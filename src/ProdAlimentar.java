/**
 * Representa um produto alimentar, que é uma especialização de {@link Produtos}.
 * Inclui informações específicas, como se o produto é biológico, e métodos abstratos
 * para calcular o IVA e exibir os detalhes do produto.
 */
public abstract class ProdAlimentar extends Produtos {

    /**
     * Indica se o produto alimentar é biológico.
     */
    protected boolean biologico;

    /**
     * Construtor com parâmetros para inicializar os atributos do produto alimentar.
     *
     * @param codigo        Código único do produto.
     * @param nome          Nome do produto.
     * @param descricao     Descrição do produto.
     * @param quantidade    Quantidade do produto.
     * @param valor_unitario Valor unitário do produto (sem IVA).
     * @param biologico     Indica se o produto é biológico.
     */
    public ProdAlimentar(String codigo, String nome, String descricao, int quantidade, double valor_unitario, boolean biologico) {
        super(codigo, nome, descricao, quantidade, valor_unitario);
        this.biologico = biologico;
    }

    /**
     * Construtor padrão sem parâmetros.
     */
    public ProdAlimentar() {
    }

    /**
     * Verifica se o produto é biológico.
     * @return ture se o produto é biológico, caso contrário false.
     */
    public boolean isBiologico() {
        return biologico;
    }

    /**
     * Define se o produto é biológico.
     * @param biologico true se o produto for biológico, caso contrário false.
     */
    public void setBiologico(boolean biologico) {
        this.biologico = biologico;
    }

    /**
     * Método abstrato para calcular o IVA (Imposto sobre o Valor Acrescentado) aplicável ao produto.
     * @param cliente O cliente para o qual o IVA será calculado.
     * @return O valor do IVA aplicado ao produto.
     */
    public abstract double calcularIVA(Clientes cliente);

    /**
     * Método abstrato para exibir as informações detalhadas do produto.
     * @param cliente O cliente relacionado à exibição do produto.
     */
    public abstract void printProduto(Clientes cliente);

    /**
     * Retorna uma representação textual do produto alimentar.
     * @return Uma string representando o produto alimentar.
     */
    @Override
    public String toString() {
        return "ProdAlimentar{" +
                "biologico=" + biologico +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", valor_unitario=" + valor_unitario +
                '}';
    }
}
