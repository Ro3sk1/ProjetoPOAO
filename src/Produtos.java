import java.io.Serializable;

/**
 * Classe abstrata que representa um produto, contendo informações como código, nome, descrição,
 * quantidade, e valor unitário. Implementa a interface Serializable e define métodos abstratos
 * para cálculo de IVA e impressão de detalhes específicos.
 *
 * @author Guilherme Rosmaninho
 * @author Tiago Mustra
 * @version 1.0
 */
public abstract class Produtos implements Serializable {

    /** Código único do produto. */
    protected String codigo;

    /** Nome do produto. */
    protected String nome;

    /** Descrição do produto. */
    protected String descricao;

    /** Quantidade por embalagem do produto. */
    protected int quantidade;

    /** Valor unitário do produto. */
    protected double valor_unitario;

    /**
     * Construtor da classe Produtos que inicializa todos os campos do produto.
     *
     * @param codigo         o código único do produto
     * @param nome           o nome do produto
     * @param descricao      a descrição do produto
     * @param quantidade     a quantidade por embalagem do produto
     * @param valor_unitario o valor unitário do produto
     */
    public Produtos(String codigo, String nome, String descricao, int quantidade, double valor_unitario) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor_unitario = valor_unitario;
    }

    /**
     * Construtor padrão da classe Produtos.
     */
    public Produtos() {
    }

    /**
     * Obtém o código do produto.
     *
     * @return o código do produto
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Define o código do produto.
     *
     * @param codigo o novo código do produto
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtém o nome do produto.
     *
     * @return o nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome o novo nome do produto
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a descrição do produto.
     *
     * @return a descrição do produto
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do produto.
     *
     * @param descricao a nova descrição do produto
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém a quantidade por embalagem do produto.
     *
     * @return a quantidade do produto
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade por embalagem do produto.
     *
     * @param quantidade a nova quantidade do produto
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Obtém o valor unitário do produto.
     *
     * @return o valor unitário do produto
     */
    public double getValor_unitario() {return valor_unitario;}

    /**
     * Define o valor unitário do produto.
     *
     * @param valor_unitario o novo valor unitário do produto
     */
    public void setValor_unitario(double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    /**
     * Método abstrato para calcular o IVA baseado na localizacao cliente.
     *
     * @param cliente o cliente relacionado ao produto
     * @return o valor do IVA
     */
    public abstract double calcularIVA(Clientes cliente);

    /**
     * Método abstrato para imprimir detalhes específicos do produto com base no cliente.
     *
     * @param cliente o cliente relacionado ao produto
     */
    public abstract void printProduto(Clientes cliente);

    /**
     * Retorna uma representação em string do objeto Produto para um cliente específico.
     * @return uma string representando o produto
     */
    public String toString() {
        return "Produtos{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", valor_unitario=" + valor_unitario +
                '}';
    }
}
