public class ProdFarmaciaComPrescricao extends ProdFarmacia{

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

        double IVA_PORTUGAL_CONTINENTAL = 0.06;
        double IVA_MADEIRA = 0.05;
        double IVA_ACORES = 0.04;

        return switch (cliente.getLocalizacao()) {
            case "Portugal Continental" -> IVA_PORTUGAL_CONTINENTAL;
            case "Madeira" -> IVA_MADEIRA;
            case "Açores" -> IVA_ACORES;
            default -> 0;
        };
    }

    public void printProduto(Clientes cliente) {
        double precoComIva = valor_unitario * (1 + calcularIVA(cliente));
        double precoIvaTotal = valor_unitario * quantidade * calcularIVA(cliente);
        double precoTotal = precoComIva * quantidade;

        System.out.println(Cores.AZUL.getCode() + nome + Cores.RESET.getCode() + Cores.NEGRITO.getCode() + " (" + descricao + ") - " + Cores.RESET.getCode() + Cores.AMARELO.getCode() + String.format("%.2f", precoTotal) + "€ " + Cores.VERMELHO.getCode() + "(IVA: " + String.format("%.2f", precoIvaTotal) + "€ | " + String.format("%.1f", calcularIVA(cliente) * 100) + "%)" + Cores.RESET.getCode());
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
