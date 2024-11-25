public class ProdFarmaciaComPrescricao extends ProdFarmacia{

    private String VERMELHO = "\033[0;31m";
    private String VERDE = "\033[0;32m";
    private String AMARELO = "\033[0;33m";
    private String AZUL = "\033[0;34m";
    private String MAGENTA = "\033[0;35m";
    private String NEGRITO = "\033[1m";
    private String RESET = "\033[0m";

    protected String medicoPrescritor;

    public ProdFarmaciaComPrescricao(String codigo, String nome, String descricao, int quantidade, double valorUnitario, String medicoPrescritor) {
        super(codigo, nome, descricao, quantidade, valorUnitario);
        this.medicoPrescritor = medicoPrescritor;
    }

    public ProdFarmaciaComPrescricao() {
    }

    public String getMedicoPrescritor() {
        return medicoPrescritor;
    }

    public void setMedicoPrescritor(String medicoPrescritor) {
        this.medicoPrescritor = medicoPrescritor;
    }

    public double calcularIVA(Clientes cliente) {
        return switch (cliente.getLocalizacao()) {
            case "Portugal Continental" -> 0.06;
            case "Madeira" -> 0.05;
            case "Açores" -> 0.04;
            default -> 0;
        };
    }

    public void printProduto(Clientes cliente) {
        double precoComIva = valor_unitario * (1 + calcularIVA(cliente));
        double precoIvaTotal = valor_unitario * quantidade * calcularIVA(cliente);
        double precoTotal = precoComIva * quantidade;

        System.out.println(AZUL + nome + RESET + NEGRITO + " (" + descricao + ") - " + RESET + AMARELO + String.format("%.2f", precoTotal) + "€ " + VERMELHO + "(IVA: " + String.format("%.2f", precoIvaTotal) + "€ | " + String.format("%.1f", calcularIVA(cliente) * 100) + "%)" + RESET);
    }

    @Override
    public String toString() {
        return "ProdFarmaciaComPrescricao{" +
                "medicoPrescritor='" + medicoPrescritor + '\'' +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", valor_unitario=" + valor_unitario +
                '}';
    }
}
