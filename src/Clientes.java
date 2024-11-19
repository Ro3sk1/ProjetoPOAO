import java.io.Serializable;

public class Clientes implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String numero_contribuinte;
    private String localizacao;

    public Clientes() {
    }

    public Clientes(String nome, String numero_contribuinte, String localizacao) {
        this.nome = nome;
        this.numero_contribuinte = numero_contribuinte;
        this.localizacao = localizacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero_contribuinte() {
        return numero_contribuinte;
    }

    public void setNumero_contribuinte(String numero_contribuinte) {
        this.numero_contribuinte = numero_contribuinte;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}