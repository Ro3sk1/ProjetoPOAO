/**
 * Enumeração que representa diferentes cores e estilos de texto ANSI.
 * Cada cor ou estilo é representado por um código ANSI correspondente.
 *
 *  @author Guilherme Rosmaninho
 *  @author Tiago Mustra
 *  @version 1.0
 */
public enum Cores {

    /**
     * Cor vermelha.
     */
    VERMELHO("\033[0;31m"),

    /**
     * Cor verde.
     */
    VERDE("\033[0;32m"),

    /**
     * Cor amarela.
     */
    AMARELO("\033[0;33m"),

    /**
     * Cor azul.
     */
    AZUL("\033[0;34m"),

    /**
     * Cor magenta.
     */
    MAGENTA("\033[0;35m"),

    /**
     * Estilo de texto negrito.
     */
    NEGRITO("\033[1m"),

    /**
     * Reset de cor e estilo.
     */
    RESET("\033[0m");

    private final String code;

    /**
     * Construtor da enumeração Cores.
     *
     * @param code O código ANSI correspondente à cor ou estilo.
     */
    Cores(String code) {
        this.code = code;
    }

    /**
     * Obtém o código ANSI da cor ou estilo.
     *
     * @return O código ANSI.
     */
    public String getCode() {
        return code;
    }
}
