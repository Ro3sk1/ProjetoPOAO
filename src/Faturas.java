import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class Faturas implements Serializable {

    protected int id;
    protected Clientes cliente;
    protected Date data;
    protected double valor_sem_iva, valor_iva, valor_total;
    protected List<Produtos> produtosList;

    public Faturas(int id, Clientes cliente, Date data, double valor_sem_iva, double valor_iva, double valor_total, List<Produtos> produtosList) {
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

    public Date getData() {return data;}

    public void setData(Date data) {this.data = data;}

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