import java.io.Serializable;

public class Clientes implements Serializable {

    protected String nome;
    protected String numero_contribuinte;
    protected String localizacao;

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