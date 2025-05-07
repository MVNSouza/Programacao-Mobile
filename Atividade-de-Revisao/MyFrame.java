import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame {
        public MyFrame() {
            // Basic frame setup
            setTitle("Criador de pedidos");
            setSize(500, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Create a main panel with BorderLayout
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout(10, 10)); // gaps between components
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // padding

            // Create components
            // 1. Text Area
            JTextArea textArea = new JTextArea(5, 10);
            textArea.setEditable(false);
            textArea.setFocusable(false);
            textArea.setLineWrap(true);

            JScrollPane scrollPane = new JScrollPane(textArea); // Add scrolling

            // 2. Buttons Panel (using FlowLayout)
            JPanel buttonPanel = new JPanel(new FlowLayout());

            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(e -> {
                // Button click handler
                String text = textArea.getText();
                JOptionPane.showMessageDialog(this, "You entered: " + text);
            });

            JButton clearButton = new JButton("Clear");
            clearButton.addActionListener(e -> textArea.setText(""));


            // 4. Label
            JLabel label = new JLabel("Enter your text:");

            // 5. Checkbox
            JCheckBox checkBox = new JCheckBox("Enable feature");

            // 6. Radio Buttons
            JRadioButton radio1 = new JRadioButton("Option 1");
            JRadioButton radio2 = new JRadioButton("Option 2");
            ButtonGroup radioGroup = new ButtonGroup();
            radioGroup.add(radio1);
            radioGroup.add(radio2);

            // 7. Combo Box (Dropdown)
            String[] options = {"Option 1", "Option 2", "Option 3"};
            JComboBox<String> comboBox = new JComboBox<>(options);

            // Add components to button panel
            buttonPanel.add(submitButton);
            buttonPanel.add(clearButton);

            // Create left panel for controls
            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            leftPanel.add(label);
            leftPanel.add(checkBox);
            leftPanel.add(radio1);
            leftPanel.add(radio2);
            leftPanel.add(comboBox);

            // Add all panels to main panel
            mainPanel.add(leftPanel, BorderLayout.WEST);
            mainPanel.add(scrollPane, BorderLayout.CENTER);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);

            // Add main panel to frame
            add(mainPanel);

            // Make the frame visible
            setVisible(true);


        }

}

