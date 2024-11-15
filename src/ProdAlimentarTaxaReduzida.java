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

}
