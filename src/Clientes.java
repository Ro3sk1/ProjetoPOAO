import java.io.Serializable;

/**
 * Classe que representa um cliente, contendo informações como nome, número de contribuinte
 * e localização. Implementa a interface Serializable para permitir a serialização do objeto.
 *
 * @author Guilherme Rosmaninho
 * @author Tiago Mustra
 * @version 1.0
 *
 */

public class Clientes implements Serializable {

    /** Nome do cliente. */
    protected String nome;

    /** Número de contribuinte do cliente. */
    protected String numero_contribuinte;

    /** Localização do cliente. */
    protected String localizacao;

    /**
     * Construtor padrão da classe Clientes.
     */
    public Clientes() {
    }


    /**
     * Construtor da classe Clientes que inicializa todos os campos.
     *
     * @param nome                o nome do cliente
     * @param numero_contribuinte o número de contribuinte do cliente
     * @param localizacao         a localização do cliente
     */
    public Clientes(String nome, String numero_contribuinte, String localizacao) {
        this.nome = nome;
        this.numero_contribuinte = numero_contribuinte;
        this.localizacao = localizacao;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return o nome do cliente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente.
     *
     * @param nome o novo nome do cliente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o número de contribuinte do cliente.
     *
     * @return o número de contribuinte do cliente
     */
    public String getNumero_contribuinte() {
        return numero_contribuinte;
    }

    /**
     * Define o número de contribuinte do cliente.
     *
     * @param numero_contribuinte o novo número de contribuinte do cliente
     */
    public void setNumero_contribuinte(String numero_contribuinte) {
        this.numero_contribuinte = numero_contribuinte;
    }

    /**
     * Obtém a localização do cliente.
     *
     * @return a localização do cliente
     */
    public String getLocalizacao() {
        return localizacao;
    }

    /**
     * Define a localização do cliente.
     *
     * @param localizacao a nova localização do cliente
     */
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * Imprime as informações do cliente.
     */
    public void printCliente(){
        System.out.println("    | Nome: " + Cores.AZUL.getCode() + nome + Cores.RESET.getCode());
        System.out.println("    | Número de contribuinte: " + Cores.AZUL.getCode() + numero_contribuinte + Cores.RESET.getCode());
        System.out.println("    | Localização: " + Cores.AZUL.getCode() + localizacao + Cores.RESET.getCode());
    }

    /**
     * Retorna uma representação em string do objeto Clientes.
     *
     * @return uma string representando o objeto
     */
    @Override
    public String toString() {
        return "Clientes{" +
                "nome='" + nome + '\'' +
                ", numero_contribuinte='" + numero_contribuinte + '\'' +
                ", localizacao='" + localizacao + '\'' +
                '}';
    }
}