import java.util.List;

public class ProdAlimentarTaxaReduzida extends ProdAlimentar{

    private String VERMELHO = "\033[0;31m";
    private String VERDE = "\033[0;32m";
    private String AMARELO = "\033[0;33m";
    private String AZUL = "\033[0;34m";
    private String MAGENTA = "\033[0;35m";
    private String NEGRITO = "\033[1m";
    private String RESET = "\033[0m";

    protected List<String> certificacoes;

    public ProdAlimentarTaxaReduzida(String codigo, String nome, String descricao, int quantidade, double valor_unitario, boolean biologico, List<String> certificacoes) {
        super(codigo, nome, descricao, quantidade, valor_unitario, biologico);
        this.certificacoes = certificacoes;
    }

    public ProdAlimentarTaxaReduzida() {
    }

    public List<String> getCertificacoes() {
        return certificacoes;
    }

    public void setCertificacoes(List<String> certificacoes) {
        this.certificacoes = certificacoes;
    }

    public double calcularIVA(Clientes cliente) {
        double iva = 0;
        switch (cliente.getLocalizacao()) {
            case "Portugal Continental" -> iva = 0.06;
            case "Madeira" -> iva = 0.05;
            case "Açores" -> iva = 0.04;
            default -> iva = 0;
        }
        if (getCertificacoes().size() == 4) {
            iva -= 0.01;
        }
        if(isBiologico()) {
            iva = iva*0.9;
        }
        return iva;
    }

    public String toString(Clientes cliente) {
        double precoComIva = valor_unitario * (1 + calcularIVA(cliente));
        double precoIvaTotal = valor_unitario * quantidade * calcularIVA(cliente);
        double precoTotal = precoComIva * quantidade;

        return AZUL + nome + RESET + NEGRITO + " (" + descricao + ") - " + RESET + AMARELO + String.format("%.2f", precoTotal) + "€ " + VERMELHO + "(IVA: " + String.format("%.2f", precoIvaTotal) + "€ | " + String.format("%.1f", calcularIVA(cliente) * 100) + "%)" + RESET;
    }
}
