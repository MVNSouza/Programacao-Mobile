import java.util.HashMap;

public class playground {
    public static void main(String[] args) {

        LivroFisico fisico1 = new LivroFisico("Java Essencial", "Autor A", 2020, 100.0, 10, 500);
        LivroFisico fisico2 = new LivroFisico("Estrutura de Dados", "Autor B", 2018, 80.0, 5, 300);

        LivroDigital digital1 = new LivroDigital("Python para Iniciantes", "Autor C", 2021, 50.0, 15, 10);
        LivroDigital digital2 = new LivroDigital("Machine Learning", "Autor D", 2022, 120.0, 7, 25);


        Cliente cliente = new Cliente("Marcos", "marcos@email.com", 500.0);


        GerenciadorDesconto.aplicarDesconto(fisico1, 10); // 10% de desconto
        GerenciadorDesconto.aplicarDesconto(digital2, 20); // 20% de desconto


        System.out.println("\n--- ESTOQUE ANTES ---");
        imprimirEstoque(fisico1, fisico2, digital1, digital2);


        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(fisico1, 2);
        pedido.adicionarItem(digital2, 1);

        System.out.printf("Total do pedido: R$ %.2f\n", pedido.calcularTotal());


        try {
            pedido.finalizar();
        } catch (Exception e) {
            System.out.println("Erro ao finalizar pedido: " + e.getMessage());
        }

        System.out.println("\n--- ESTOQUE DEPOIS ---");
        imprimirEstoque(fisico1, fisico2, digital1, digital2);

        System.out.printf("\nSaldo restante do cliente: R$ %.2f\n", cliente.getSaldoCarteira());
    }

    public static void imprimirEstoque(Livro... livros) {
        for (Livro livro : livros) {
            System.out.printf("%s - Estoque: %d\n", livro.getTitulo(), livro.getEstoque());
        }
    }
}
