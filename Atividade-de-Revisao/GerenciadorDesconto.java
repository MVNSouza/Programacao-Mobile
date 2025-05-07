public class GerenciadorDesconto {

    public static void aplicarDesconto(Livro livro, double percentual) {
        double novoPreco = livro.getPrecoBase() * (1 - percentual / 100.0);
        livro.setPrecoBase(novoPreco);
    }
}
