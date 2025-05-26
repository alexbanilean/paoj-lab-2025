package examples;

import java.awt.*;             // Pentru componente AWT
import java.awt.event.*;       // Pentru tratarea evenimentelor

// Exemplu FereastraAWT
public class Ex1_AWT extends Frame implements ActionListener {

    private Label labelMesaj;
    private TextField textNume;
    private Button butonSalut;

    public Ex1_AWT() {
        // Setăm titlul ferestrei
        setTitle("Exemplu AWT - Salut");

        // Setăm layout-ul: folosim FlowLayout pentru aranjare simplă
        setLayout(new FlowLayout());

        // Inițializăm componentele
        labelMesaj = new Label("Introdu numele tău:");
        textNume = new TextField(20); // Câmp text cu 20 de coloane
        butonSalut = new Button("Salută-mă!");

        // Adăugăm componentele în fereastră
        add(labelMesaj);
        add(textNume);
        add(butonSalut);

        // Înregistrăm ascultătorul de evenimente
        butonSalut.addActionListener(this);

        // Gestionăm închiderea ferestrei
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();       // Eliberăm resursele ferestrei
                System.exit(0);  // Terminăm aplicația
            }
        });

        // Setăm dimensiunea și facem fereastra vizibilă
        setSize(300, 150);
        setVisible(true);
    }

    // Metoda apelată când se apasă butonul
    @Override
    public void actionPerformed(ActionEvent e) {
        String nume = textNume.getText().trim();
        if (!nume.isEmpty()) {
            labelMesaj.setText("Salut, " + nume + "!");
        } else {
            labelMesaj.setText("Te rog introdu un nume.");
        }
    }

    // Punctul de pornire al aplicației
    public static void main(String[] args) {
        new Ex1_AWT();  // Creăm instanța ferestrei
    }
}
