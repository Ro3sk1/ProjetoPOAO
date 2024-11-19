import java.io.Serializable;

public class Produtos implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String codigo;
    protected String nome;
    protected String descricao;
    protected int quantidade;
    protected double valor_unitario;

    public Produtos(String codigo, String nome, String descricao, int quantidade, double valor_unitario) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor_unitario = valor_unitario;
    }

    public Produtos() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public double calcularIVA(Clientes cliente) {
        double iva = 0;
        switch (cliente.getLocalizacao()) {
            case "Portugal Continental" -> iva = 0.06;
            case "Madeira" -> iva = 0.05;
            case "Açores" -> iva = 0.04;
        }
        return iva;
    }
}
