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
        }
        if (getCertificacoes().size() == 4) {
            iva -= 0.01;
        }
        if(isBiologico()) {
            iva = iva*0.9;
        }
        return iva;
    }

}
