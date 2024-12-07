import java.io.Serializable;
import java.util.List;

public class Faturas implements Serializable {

    protected int id;
    protected Clientes cliente;
    protected Data data;
    protected double valor_sem_iva, valor_iva, valor_total;
    protected List<Produtos> produtosList;

    public Faturas(int id, Clientes cliente, Data data, double valor_sem_iva, double valor_iva, double valor_total, List<Produtos> produtosList) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.produtosList = produtosList;
        this.valor_sem_iva = valor_sem_iva;
        this.valor_iva = valor_iva;
        this.valor_total = valor_total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Data getData() {return data;}

    public void setData(Data data) {this.data = data;}

    public List<Produtos> getProdutosList() {
        return produtosList;
    }

    public void setProdutosList(List<Produtos> produtosList) {
        this.produtosList = produtosList;
    }

    public double getValor_sem_iva() {
        return valor_sem_iva;
    }

    public void setValor_sem_iva(double valor_sem_iva) {
        this.valor_sem_iva = valor_sem_iva;
    }

    public double getValor_iva() {
        return valor_iva;
    }

    public void setValor_iva(double valor_iva) {
        this.valor_iva = valor_iva;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

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