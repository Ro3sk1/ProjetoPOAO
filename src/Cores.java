public enum Cores {
    VERMELHO("\033[0;31m"),
    VERDE("\033[0;32m"),
    AMARELO("\033[0;33m"),
    AZUL("\033[0;34m"),
    MAGENTA("\033[0;35m"),
    NEGRITO("\033[1m"),
    RESET("\033[0m");

    private final String code;

    Cores(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

