import java.io.Serializable;
import java.util.List;

public class Faturas implements Serializable {
    private static final long serialVersionUID = 1L;

    protected int id;
    protected Clientes cliente;
    protected int dia, mes, ano;
    protected List<Produtos> produtosList;

    public Faturas(int id, Clientes cliente, int dia, int mes, int ano, List<Produtos> produtosList) {
        this.id = id;
        this.cliente = cliente;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.produtosList = produtosList;
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

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public List<Produtos> getProdutosList() {
        return produtosList;
    }

    public void setProdutosList(List<Produtos> produtosList) {
        this.produtosList = produtosList;
    }
}