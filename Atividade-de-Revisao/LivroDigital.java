public class LivroDigital extends Livro {
    private double tamanhoArquivoMB;

    public LivroDigital(String titulo, String autor, int anoPublicacao, double valor, Integer estoque, double tamanhoArquivoMB) {
        super(titulo, autor, anoPublicacao, valor, estoque);
        this.tamanhoArquivoMB = tamanhoArquivoMB;
    }

    @Override
    public String exibirDetalhes() {
        return super.exibirDetalhes() + String.format("Tamanho: %.0fMB", tamanhoArquivoMB);
    }

    @Override
    public Double calcularPrecoBase() {
        return super.calcularPrecoBase() * 1.15;
    }

    public double getTamanhoArquivoMB() {
        return tamanhoArquivoMB;
    }

    public void setTamanhoArquivoMB(double tamanhoArquivoMB) {
        this.tamanhoArquivoMB = tamanhoArquivoMB;
    }
}
