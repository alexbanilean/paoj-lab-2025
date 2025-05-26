package examples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Mini joc interactiv Swing: Ghicește Numărul
 * Demonstrează:
 * - Folosirea mai multor panouri și layout-uri
 * - Event Dispatch Thread și invokeLater
 * - Tratarea evenimentelor
 * - Generarea aleatoare a numerelor
 * - Validare de input și feedback către utilizator
 */
public class Ex2_Swing {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameFrame());
    }
}

class GameFrame extends JFrame implements ActionListener {

    private JLabel instructiuniLabel;
    private JTextField inputField;
    private JButton guessButton, resetButton;
    private JTextArea feedbackArea;
    private int numarSecret;
    private int incercari;

    public GameFrame() {
        super("Joc: Ghicește Numărul!");

        // Inițializare joc
        initGame();

        // Setări fereastră
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panou nord: Instrucțiuni
        instructiuniLabel = new JLabel("Introdu un număr între 1 și 100:");
        instructiuniLabel.setHorizontalAlignment(JLabel.CENTER);
        add(instructiuniLabel, BorderLayout.NORTH);

        // Panou centru: TextField și buton Guess
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(10);
        guessButton = new JButton("Ghicește");
        guessButton.addActionListener(this);
        inputPanel.add(inputField);
        inputPanel.add(guessButton);
        add(inputPanel, BorderLayout.CENTER);

        // Panou sud: Feedback și buton Reset
        JPanel feedbackPanel = new JPanel(new BorderLayout());
        feedbackArea = new JTextArea(5, 40);
        feedbackArea.setEditable(false);
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);
        feedbackArea.setBorder(BorderFactory.createTitledBorder("Feedback"));
        feedbackPanel.add(new JScrollPane(feedbackArea), BorderLayout.CENTER);

        resetButton = new JButton("Resetare joc");
        resetButton.addActionListener(e -> resetGame());
        feedbackPanel.add(resetButton, BorderLayout.SOUTH);

        add(feedbackPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void initGame() {
        Random rand = new Random();
        numarSecret = rand.nextInt(100) + 1;
        incercari = 0;
    }

    private void resetGame() {
        initGame();
        inputField.setText("");
        feedbackArea.setText("");
        instructiuniLabel.setText("Introdu un număr între 1 și 100:");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText().trim();
        try {
            int numar = Integer.parseInt(input);
            incercari++;
            if (numar < 1 || numar > 100) {
                feedbackArea.append("Numărul trebuie să fie între 1 și 100.\n");
            } else if (numar < numarSecret) {
                feedbackArea.append("Prea mic! Încearcă din nou.\n");
            } else if (numar > numarSecret) {
                feedbackArea.append("Prea mare! Încearcă din nou.\n");
            } else {
                feedbackArea.append("Felicitări! Ai ghicit numărul în " + incercari + " încercări.\n");
                instructiuniLabel.setText("Ai câștigat! Apasă Resetare pentru a juca din nou.");
            }
        } catch (NumberFormatException ex) {
            feedbackArea.append("Te rog introdu un număr valid.\n");
        }
        inputField.setText("");
    }
}

