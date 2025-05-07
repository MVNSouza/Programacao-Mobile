import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private Cliente cliente;
    private JTextField emailField;
    private JTextField nomeField;
    private JTextField saldoField;

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Saldo Inicial:"));
        saldoField = new JTextField();
        panel.add(saldoField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            try {
                String nome = nomeField.getText().trim();
                String email = emailField.getText().trim();
                double saldo = Double.parseDouble(saldoField.getText().trim());

                if (nome.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Por favor, preencha todos os campos",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                cliente = new Cliente(nome, email, saldo);
                dispose(); // Close login window
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, insira um valor válido para o saldo",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(loginButton);

        add(panel);
        pack();
    }

    public Cliente getCliente() {
        return cliente;
    }
}