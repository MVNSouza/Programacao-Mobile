import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Pedido {
    private Cliente cliente;
    private HashMap<Livro, Integer> pedidos =  new HashMap<>();

    public Pedido(Cliente cliente, HashMap<Livro, Integer> pedidos) {
        this.cliente = cliente;
        this.pedidos.putAll(pedidos);
    }
    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public HashMap<Livro, Integer> getPedidos() {
        return pedidos;
    }

    public void addPedido(Livro livro, Integer qtd) {
        this.pedidos.put(livro, qtd);
    }

    public Double calcularTotal(){
        double total = 0.0;
        for (HashMap.Entry<Livro, Integer> livro : pedidos.entrySet()) {
            total += livro.getKey().calcularPrecoBase() * livro.getValue();
        }
        return total;
    }

    public void finalizar() throws IllegalArgumentException{
        if (calcularTotal() < cliente.getSaldoCarteira()){
            cliente.setSaldoCarteira(getCliente().getSaldoCarteira() - calcularTotal());

            for (HashMap.Entry<Livro, Integer> livro : pedidos.entrySet()) {
                livro.getKey().setEstoque(livro.getKey().getEstoque() - livro.getValue());
            }

            JOptionPane.showMessageDialog(null, "Pedido finalizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente!", "Erro", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Saldo insuficiente!");
        }

    }

    public void adicionarItem(Livro livro, int quantidade){
        pedidos.put(livro, quantidade);
    }

    public void adicionarItem(Livro livro){
        pedidos.put(livro, 1);
    }

    public void limparPedido(){
        pedidos.clear();
    }

    public void setCliente(Cliente cliente){
        if (cliente != null){
            this.cliente.setNome(cliente.getNome());
            this.cliente.setEmail(cliente.getEmail());
            this.cliente.setSaldoCarteira(cliente.getSaldoCarteira());
        } else {
            this.cliente.setNome("");
            this.cliente.setEmail("");
            this.cliente.setSaldoCarteira(0.0);
        }
    }
}
