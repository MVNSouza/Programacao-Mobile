public class LivroFisico extends Livro {

    private Double pesoEmGramas;

    public LivroFisico(String titulo, String autor, int anoPublicacao, double precoBase, Integer estoque, double pesoGramas) {
        super(titulo, autor, anoPublicacao, precoBase, estoque);
        if (pesoGramas <= 0) {
            this.pesoEmGramas = null;
        } else {
            this.pesoEmGramas = pesoGramas;
        }
    }

    @Override
    public String exibirDetalhes() {
        return super.exibirDetalhes() + String.format("Peso: %.1f\n", pesoEmGramas);
    }

    @Override
    public Double calcularPrecoBase() {
        return super.calcularPrecoBase() * 1.1;
    }

    public Double getPesoEmGramas() {
        return pesoEmGramas;
    }

    public void setPesoEmGramas(Double pesoEmGramas) {
        this.pesoEmGramas = pesoEmGramas;
    }
}
