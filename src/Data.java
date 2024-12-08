import java.io.Serializable;

/**
 * Classe que representa uma data composta por dia, mês e ano.
 * Implementa a interface Serializable para permitir a serialização do objeto.
 *
 * @author Guilherme Rosmaninho
 * @author Tiago Mustra
 * @version 1.0
 */

public class Data implements Serializable {

    /** Dia da data. */
    protected int dia;

    /** Mês da data. */
    protected int mes;

    /** Ano da data. */
    protected int ano;

    /**
     * Construtor da classe Data que inicializa os campos dia, mês e ano.
     *
     * @param dia o dia da data
     * @param mes o mês da data
     * @param ano o ano da data
     */
    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    /**
     * Obtém o dia da data.
     *
     * @return o dia da data
     */
    public int getDia() {
        return dia;
    }

    /**
     * Define o dia da data.
     *
     * @param dia o novo valor para o dia
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Obtém o mês da data.
     *
     * @return o mês da data
     */
    public int getMes() {
        return mes;
    }

    /**
     * Define o mês da data.
     *
     * @param mes o novo valor para o mês
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * Obtém o ano da data.
     *
     * @return o ano da data
     */
    public int getAno() {
        return ano;
    }

    /**
     * Define o ano da data.
     *
     * @param ano o novo valor para o ano
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * Retorna uma representação em string da data no formato "dd/mm/aaaa".
     *
     * @return uma string representando a data
     */
    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }

}

