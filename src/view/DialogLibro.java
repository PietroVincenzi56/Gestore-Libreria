package view;

import model.Libro;
import model.StatoLettura;

import javax.swing.*;
import java.awt.*;


public class DialogLibro extends JDialog {

    private JTextField titoloField;
    private JTextField autoreField;
    private JTextField genereField;
    private JTextField isbnField;
    private JSpinner valutazioneSpinner;
    private JComboBox<StatoLettura> statoCombo;
    private Libro libroRisultato = null;


    public DialogLibro(JFrame parent) {
        this(parent, null);
    }

    public DialogLibro(JFrame parent, Libro libroEsistente) {
        super(parent, "Dettagli Libro", true);
        setLayout(new GridLayout(7, 2, 10, 10));
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Titolo
        add(new JLabel("Titolo:"));
        titoloField = new JTextField(libroEsistente != null ? libroEsistente.getTitolo() : "");
        add(titoloField);

        // Autore
        add(new JLabel("Autore:"));
        autoreField = new JTextField(libroEsistente != null ? libroEsistente.getAutore() : "");
        add(autoreField);

        // ISBN
        add(new JLabel("ISBN:"));
        isbnField = new JTextField(libroEsistente != null ? libroEsistente.getIsbn() : "");
        add(isbnField);

        // Genere
        add(new JLabel("Genere:"));
        genereField = new JTextField(libroEsistente != null ? libroEsistente.getGenere() : "");
        add(genereField);

        // Valutazione
        add(new JLabel("Valutazione (0–5):"));
        valutazioneSpinner = new JSpinner(new SpinnerNumberModel(
                libroEsistente != null ? libroEsistente.getScore() : 0, 0, 5, 1));
        add(valutazioneSpinner);

        // Stato lettura
        add(new JLabel("Stato lettura:"));
        statoCombo = new JComboBox<>(StatoLettura.values());
        if (libroEsistente != null) {
            statoCombo.setSelectedItem(libroEsistente.getStato());
        }
        add(statoCombo);

        // Pulsanti
        JButton conferma = new JButton("OK");
        conferma.addActionListener(e -> {
            libroRisultato = new Libro(
                    titoloField.getText(),
                    autoreField.getText(),
                    isbnField.getText(),
                    genereField.getText(),
                    (int) valutazioneSpinner.getValue(),
                    (StatoLettura) statoCombo.getSelectedItem()
            );
            dispose();
        });
        add(conferma);

        JButton annulla = new JButton("Annulla");
        annulla.addActionListener(e -> {
            libroRisultato = null;
            dispose();
        });
        add(annulla);
    }

    public Libro mostra() {
        setVisible(true);
        return libroRisultato;
    }
}