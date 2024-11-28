import java.util.List;

public class ProdAlimentarTaxaReduzida extends ProdAlimentar{

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

    public void printProduto(Clientes cliente) {
        double precoComIva = valor_unitario * (1 + calcularIVA(cliente));
        double precoIvaTotal = valor_unitario * quantidade * calcularIVA(cliente);
        double precoTotal = precoComIva * quantidade;

        System.out.println(Cores.AZUL.getCode() + nome + Cores.RESET.getCode() + Cores.NEGRITO.getCode() + " (" + descricao + ") - " + Cores.RESET.getCode() + Cores.AMARELO.getCode() + String.format("%.2f", precoTotal) + "€ " + Cores.VERMELHO.getCode() + "(IVA: " + String.format("%.2f", precoIvaTotal) + "€ | " + String.format("%.1f", calcularIVA(cliente) * 100) + "%)" + Cores.RESET.getCode());
    }

    @Override
    public String toString() {
        return "ProdAlimentarTaxaReduzida{" +
                "certificacoes=" + certificacoes +
                ", biologico=" + biologico +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", valor_unitario=" + valor_unitario +
                '}';
    }
}
