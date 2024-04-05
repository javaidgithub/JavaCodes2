

package dfasimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DFASimulatorGUI extends JFrame implements ActionListener {
    private JTextField inputField;
    private JButton simulateButton;
    private JTextArea outputArea;

    public DFASimulatorGUI() {
        setTitle("DFA Simulator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inputField = new JTextField(20);
        simulateButton = new JButton("Simulate");
        simulateButton.addActionListener(this);
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Input String: "));
        inputPanel.add(inputField);
        inputPanel.add(simulateButton);

        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == simulateButton) {
            String input = inputField.getText();
            boolean isAccepted = isAccepted(input);
            if (isAccepted) {
                outputArea.append("Accepted\n");
            } else {
                outputArea.append("Rejected\n");
            }
        }
    }

    private boolean isAccepted(String str) {
        int dfa = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (dfa) {
                case 0:
                    if (c == 'a') dfa = 3;
                    else if (c == 'b') dfa = 1;
                    else return false;
                    break;
                case 1:
                    if (c == 'a') dfa = 2;
                    else if (c == 'b') dfa = 0;
                    else return false;
                    break;
                case 2:
                    if (c == 'a') dfa = 1;
                    else if (c == 'b') dfa = 3;
                    else return false;
                    break;
                case 3:
                    if (c == 'a') dfa = 0;
                    else if (c == 'b') dfa = 2;
                    else return false;
                    break;
            }
        }
        return dfa == 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DFASimulatorGUI dfaSimulatorGUI = new DFASimulatorGUI();
            dfaSimulatorGUI.setVisible(true);
        });
    }
}
