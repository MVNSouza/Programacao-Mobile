import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private Cliente cliente;
    private Pedido p;
    private JTextArea textArea;
    private JTextArea wishlistTextArea;
    private JLabel valorTotalLabel;
    private JPanel mainPanel;

    public MyFrame() {
        // First, create and show login frame
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);

        // Wait for login to complete
        loginFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                cliente = loginFrame.getCliente();
                if (cliente != null) {
                    initializeMainFrame();
                } else {
                    dispose(); // Close this frame if login failed
                    System.exit(0);
                }
            }
        });
    }

    private void initializeMainFrame() {
        p = new Pedido(cliente);

        setTitle("Criador de pedidos - " + cliente.getNome());
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create components
        JCheckBox checkBox = new JCheckBox("Mostrar livros");
        textArea = new JTextArea(5, 3);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setLineWrap(true);

        updateBookList(checkBox.isSelected());

        JScrollPane scrollPane = new JScrollPane(textArea);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton submitButton = new JButton("Enviar pedido");
        submitButton.addActionListener(e -> {
            try {
                p.finalizar();
                updateBookList(checkBox.isSelected());
                JOptionPane.showMessageDialog(this, 
                    "Saldo atual: R$ " + String.format("%.2f", cliente.getSaldoCarteira()), 
                    "Pedido finalizado", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, 
                    ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        wishlistTextArea = new JTextArea(5, 3);
        valorTotalLabel = new JLabel("Valor total: R$ 0.00");

        JButton clearButton = new JButton("Esvaziar carrinho");
        clearButton.addActionListener(e -> {
            valorTotalLabel.setText("Valor total: R$ 0.00");
            p.limparPedido();
            wishlistTextArea.setText("");
        });

        // Left panel components
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(150, 0));
        leftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label = new JLabel("Número do livro:");
        JTextField indexField = new JTextField(2);
        indexField.setMaximumSize(new Dimension(50, 25));
        indexField.setHorizontalAlignment(JTextField.CENTER);
        indexField.setToolTipText("Insira o número do livro na lista");

        JButton addItemButton = new JButton("Adicionar");
        addItemButton.addActionListener(e -> {
            try {
                int index = Integer.parseInt(indexField.getText());
                if (index < 1 || index > Livro.livros.size()) {
                    throw new IllegalArgumentException("Índice inválido");
                }
                Livro livro = Livro.livros.get(index - 1);
                
                if (livro.getEstoque() > 0) {
                    wishlistTextArea.append(livro.getTitulo() + "\n");
                    p.adicionarItem(livro);
                    valorTotalLabel.setText("Valor total: R$ " + 
                        String.format("%.2f", p.calcularTotal()));
                    updateBookList(checkBox.isSelected());
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Livro sem estoque", "Erro", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Insira um número de livro válido", "Erro", 
                    JOptionPane.ERROR_MESSAGE);
            }
            indexField.setText("");
        });

        // Add components to panels
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);

        leftPanel.add(label);
        leftPanel.add(indexField);
        leftPanel.add(addItemButton);
        leftPanel.add(wishlistTextArea);
        leftPanel.add(valorTotalLabel);

        // Add all panels to main panel
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add checkbox to top
        mainPanel.add(checkBox, BorderLayout.NORTH);

        // Add main panel to frame
        add(mainPanel);
        setVisible(true);

        // Add checkbox listener
        checkBox.addActionListener(e -> updateBookList(checkBox.isSelected()));
    }

    private void updateBookList(boolean showEmpty) {
        textArea.setText("");
        int contador = 1;
        for (Livro livro : Livro.livros) {
            if ((showEmpty && livro.getEstoque() == 0) || livro.getEstoque() > 0) {
                textArea.append(contador + " - " + livro.getTitulo() + 
                    " - " + livro.getAutor() + 
                    " (Estoque: " + livro.getEstoque() + ")\n");
                contador++;
            }
        }
    }
}