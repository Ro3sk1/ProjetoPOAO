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

    public String getSubTipoProduto() {
        return "CP";
    }

    public double calcularIVA(Clientes cliente) {
        return switch (cliente.getLocalizacao()) {
            case "Portugal Continental" -> 0.06;
            case "Madeira" -> 0.05;
            case "Açores" -> 0.04;
            default -> 0;
        };
    }
}
