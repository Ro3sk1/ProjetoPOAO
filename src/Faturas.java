import java.io.Serializable;
import java.util.List;

/**
 * Representa uma fatura com informações sobre o cliente, data, produtos e valores associados.
 * Inclui funcionalidades para calcular e exibir os detalhes da fatura.
 *
 * @author Guilherme Rosmaninho
 * @author Tiago Mustra
 * @version 1.0
 */
public class Faturas implements Serializable {

    /**
     * Identificador único da fatura.
     */
    protected int id;

    /**
     * Cliente associado à fatura.
     */
    protected Clientes cliente;

    /**
     * Data da fatura.
     */
    protected Data data;

    /**
     * Valor total dos produtos sem IVA.
     */
    protected double valor_sem_iva;

    /**
     * Montante total do IVA aplicado.
     */
    protected double valor_iva;

    /**
     * Valor total dos produtos, incluindo o IVA.
     */
    protected double valor_total;

    /**
     * Lista de produtos associados à fatura.
     */
    protected List<Produtos> produtosList;


    /**
     * Construtor da classe Faturas.
     * @param id Identificador único da fatura.
     * @param cliente Cliente associado à fatura.
     * @param data Data da fatura.
     * @param valor_sem_iva Valor total dos produtos sem IVA.
     * @param valor_iva Montante total do IVA aplicado.
     * @param valor_total Valor total dos produtos, incluindo o IVA.
     * @param produtosList Lista de produtos associados à fatura.
     */
    public Faturas(int id, Clientes cliente, Data data, double valor_sem_iva, double valor_iva, double valor_total, List<Produtos> produtosList) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.produtosList = produtosList;
        this.valor_sem_iva = valor_sem_iva;
        this.valor_iva = valor_iva;
        this.valor_total = valor_total;
    }

    /**
     * Retorna o identificador único da fatura.
     * @return Identificador único da fatura.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador único da fatura.
     * @param id Identificador único da fatura.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o cliente associado à fatura.
     * @return Cliente associado à fatura.
     */
    public Clientes getCliente() {
        return cliente;
    }

    /**
     * Define o cliente associado à fatura.
     * @param cliente Cliente associado à fatura.
     */
    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    /**
     * Retorna a data da fatura.
     * @return Data da fatura.
     */
    public Data getData() {return data;}

    /**
     * Define a data da fatura.
     * @param data Data da fatura.
     */
    public void setData(Data data) {this.data = data;}

    /**
     * Retorna a lista de produtos associados à fatura.
     * @return Lista de produtos associados à fatura.
     */
    public List<Produtos> getProdutosList() {
        return produtosList;
    }

    /**
     * Define a lista de produtos associados à fatura.
     * @param produtosList Lista de produtos associados à fatura.
     */
    public void setProdutosList(List<Produtos> produtosList) {
        this.produtosList = produtosList;
    }

    /**
     * Obtém o valor total sem IVA.
     * @return O valor sem IVA.
     */
    public double getValor_sem_iva() {
        return valor_sem_iva;
    }

    /**
     * Define o valor total sem IVA.
     * @param valor_sem_iva O novo valor sem IVA.
     */
    public void setValor_sem_iva(double valor_sem_iva) {
        this.valor_sem_iva = valor_sem_iva;
    }

    /**
     * Obtém o montante total do IVA aplicado.
     * @return O valor do IVA.
     */
    public double getValor_iva() {
        return valor_iva;
    }

    /**
     * Define o montante total do IVA aplicado.
     * @param valor_iva O novo valor do IVA.
     */
    public void setValor_iva(double valor_iva) {
        this.valor_iva = valor_iva;
    }

    /**
     * Obtém o valor total dos produtos, incluindo o IVA.
     * @return O valor total.
     */
    public double getValor_total() {
        return valor_total;
    }

    /**
     * Define o valor total dos produtos, incluindo o IVA.
     * @param valor_total O novo valor total.
     */
    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    /**
     * Calcula o valor total sem IVA, o montante total do IVA aplicado e o
     * valor total dos produtos, incluindo o IVA e imprime a fatura formatada.
     * @param cliente O cliente associado à fatura.
     */
    public void printFatura(Clientes cliente) {
        System.out.println("    | ID: " + Cores.AZUL.getCode() + id + Cores.RESET.getCode());
        System.out.println("    | Data: " + Cores.AZUL.getCode() + data.getDia() + "/" + data.getMes() + "/" + data.getAno() + Cores.RESET.getCode());
        System.out.println("    | " + Cores.MAGENTA.getCode() + "> PRODUTOS (" + produtosList.size() + "):" + Cores.RESET.getCode());
        System.out.println("    | | ------------------------------- |");
        for (Produtos produto : produtosList) {
            double iva = produto.calcularIVA(cliente);
            double valorComIva = produto.getValor_unitario() + produto.getValor_unitario() * iva;
            System.out.println("    | ┃ Nome: " + Cores.AMARELO.getCode() + produto.getNome() + Cores.RESET.getCode());
            System.out.println("    | ┃ Quantidade: " + Cores.AMARELO.getCode() + produto.getQuantidade() + Cores.RESET.getCode());
            System.out.printf("    | ┃ Valor unitário (s/IVA): " + Cores.VERDE.getCode() + "%.2f€" + Cores.RESET.getCode() + "\n", produto.getValor_unitario());
            System.out.printf("    | ┃ IVA: " + Cores.AMARELO.getCode() + "%.2f€ " + Cores.MAGENTA.getCode() + "(%.1f%%)" + Cores.RESET.getCode() + "\n", produto.getValor_unitario() * iva, iva * 100);
            System.out.printf("    | ┃ Valor unitário (c/IVA): " + Cores.VERDE.getCode() + "%.2f€" + Cores.RESET.getCode() + "\n", valorComIva);
            System.out.println("    | | ------------------------------- |");
        }
        System.out.printf("    | PREÇO (s/IVA): " + Cores.AMARELO.getCode() + "%.2f€" + Cores.RESET.getCode() + "\n", valor_sem_iva);
        System.out.printf("    | IVA: " + Cores.AMARELO.getCode() + "%.2f€ " + Cores.MAGENTA.getCode() + "(%.1f%%)" + Cores.RESET.getCode() + "\n", valor_iva, valor_iva / valor_sem_iva * 100);
        System.out.printf("    | " + Cores.NEGRITO.getCode() + "TOTAL: " + Cores.AZUL.getCode() + "%.2f€" + Cores.RESET.getCode() + "\n", valor_total);
    }

    /**
     * Retorna uma representação textual da fatura.
     * @return A string representando a fatura.
     */
    @Override
    public String toString() {
        return "Faturas{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", data=" + data +
                ", valor_sem_iva=" + valor_sem_iva +
                ", valor_iva=" + valor_iva +
                ", valor_total=" + valor_total +
                ", produtosList=" + produtosList +
                '}';
    }
}