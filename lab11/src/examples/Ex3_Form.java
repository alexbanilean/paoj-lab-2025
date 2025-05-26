package examples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Exemplu avansat de aplicație Swing: Formular de înregistrare
 * Demonstrează utilizarea componentelor Swing pentru colectarea de date:
 * - JTextField, JPasswordField, JComboBox, JCheckBox, JRadioButton
 * - Layout manageri: GridBagLayout
 * - Gruparea butoanelor radio
 * - Validare simplă a datelor și afisarea într-un dialog
 */
public class Ex3_Form {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormularInregistrare());
    }
}

class FormularInregistrare extends JFrame implements ActionListener {

    private JTextField textNume;
    private JPasswordField textParola;
    private JComboBox<String> comboOras;
    private JCheckBox checkAgree;
    private JRadioButton radioMasculin, radioFeminin;
    private JButton butonTrimite;
    private ButtonGroup grupGen;

    public FormularInregistrare() {
        super("Formular de Înregistrare Utilizator");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etichetă și câmp nume
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nume utilizator:"), gbc);
        gbc.gridx = 1;
        textNume = new JTextField(20);
        add(textNume, gbc);

        // Etichetă și câmp parolă
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Parolă:"), gbc);
        gbc.gridx = 1;
        textParola = new JPasswordField(20);
        add(textParola, gbc);

        // Etichetă și combo box oraș
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Oraș:"), gbc);
        gbc.gridx = 1;
        comboOras = new JComboBox<>(new String[] {"București", "Cluj-Napoca", "Timișoara", "Iași", "Constanța"});
        add(comboOras, gbc);

        // Gen (radio buttons)
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Gen:"), gbc);
        gbc.gridx = 1;
        JPanel panouGen = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioMasculin = new JRadioButton("Masculin");
        radioFeminin = new JRadioButton("Feminin");
        grupGen = new ButtonGroup();
        grupGen.add(radioMasculin);
        grupGen.add(radioFeminin);
        panouGen.add(radioMasculin);
        panouGen.add(radioFeminin);
        add(panouGen, gbc);

        // Checkbox Termeni
        gbc.gridx = 1; gbc.gridy = 4;
        checkAgree = new JCheckBox("Sunt de acord cu termenii și condițiile");
        add(checkAgree, gbc);

        // Buton Trimite
        gbc.gridx = 1; gbc.gridy = 5;
        butonTrimite = new JButton("Trimite");
        butonTrimite.addActionListener(this);
        add(butonTrimite, gbc);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nume = textNume.getText().trim();
        String parola = new String(textParola.getPassword());
        String oras = (String) comboOras.getSelectedItem();
        String gen = radioMasculin.isSelected() ? "Masculin" : (radioFeminin.isSelected() ? "Feminin" : "Nespecificat");

        if (nume.isEmpty() || parola.isEmpty() || !checkAgree.isSelected() || gen.equals("Nespecificat")) {
            JOptionPane.showMessageDialog(this, "Completează toate câmpurile și acceptă termenii.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Afișare date într-un dialog
        StringBuilder mesaj = new StringBuilder();
        mesaj.append("Înregistrare reușită!\n");
        mesaj.append("Nume: ").append(nume).append("\n");
        mesaj.append("Parolă: ").append(parola).append("\n");
        mesaj.append("Oraș: ").append(oras).append("\n");
        mesaj.append("Gen: ").append(gen);

        JOptionPane.showMessageDialog(this, mesaj.toString(), "Succes", JOptionPane.INFORMATION_MESSAGE);
    }
}
