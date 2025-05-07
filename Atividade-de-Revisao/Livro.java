import java.util.ArrayList;

public class Livro {
    private String titulo, autor;
    private Integer anoPublicacao;
    private Double precoBase;
    private Integer estoque;

    public Livro(String titulo, String autor, int anoPublicacao, double valor,  Integer estoque) {
        this.titulo = titulo;
        this.autor = autor;
        if (anoPublicacao >= 0) {
            this.anoPublicacao = anoPublicacao;
        } else {
            this.anoPublicacao = null;
        }
        if (valor >= 0) {
            this.precoBase = valor;
        } else {
            this.precoBase = null;
        }
        this.estoque = estoque;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        if (anoPublicacao >= 0) {
            this.anoPublicacao = anoPublicacao;
        }
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        if (precoBase >= 0) {
            this.precoBase = precoBase;
        }
    }

    public void setValor(double valor) {
        if  (valor >= 0) {
            this.precoBase = valor;
        }
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        if (estoque >= 0) {
            this.estoque = estoque;
        }
    }

    public String exibirDetalhes() {
        return String.format("Livro: %s - Autor: %s\n" + "Ano de Publicação: %d\n" +
                "Preço Base: R$ %.2f\n" +
                "%s\n", titulo, autor, anoPublicacao, precoBase, (estoque > 0 ? "✅ Disponível " + "--- Quantidade em estoque = " + estoque : "❌ Sem exemplares no estoque"));

    }

    public Double calcularPrecoBase() {
        return precoBase;
    }

    public static void detalharLivros(ArrayList<Livro> livros) {
        for (Livro livro : livros) {
            System.out.println(livro.getTitulo() + " - Preço : R$" + String.format("%.2f",livro.calcularPrecoBase()));
        }
    }
}
