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
}
