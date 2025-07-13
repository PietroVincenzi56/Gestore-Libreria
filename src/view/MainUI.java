package view;

import controller.LibreriaManager;
import model.Libro;
import model.StatoLettura;
import java.util.Observer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;

public class MainUI extends JFrame implements Observer {

    private BarraRicerca barra;
    private ListaLibri tabella;

    public MainUI(LibreriaManager manager) throws IOException {
        super("Gestione Libreria Personale");

        manager.addObserver(this);

        barra = new BarraRicerca(this);
        tabella = new ListaLibri();

        setLayout(new BorderLayout());
        add(barra, BorderLayout.NORTH);
        add(tabella, BorderLayout.CENTER);

        JButton aggiungi = new JButton("+ Aggiungi libro");
        aggiungi.addActionListener(e -> {
            JTextField titoloField = new JTextField();
            JTextField autoreField = new JTextField();
            JTextField isbnField = new JTextField();
            JTextField genereField = new JTextField();
            JComboBox<Integer> valutazioneBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
            JComboBox<StatoLettura> statoBox = new JComboBox<>(StatoLettura.values());

            JPanel panel = new JPanel(new GridLayout(0, 2));
            panel.add(new JLabel("Titolo:"));
            panel.add(titoloField);
            panel.add(new JLabel("Autore:"));
            panel.add(autoreField);
            panel.add(new JLabel("ISBN:"));
            panel.add(isbnField);
            panel.add(new JLabel("Genere:"));
            panel.add(genereField);
            panel.add(new JLabel("Valutazione:"));
            panel.add(valutazioneBox);
            panel.add(new JLabel("Stato lettura:"));
            panel.add(statoBox);

            int result = JOptionPane.showConfirmDialog(this, panel, "Aggiungi Libro",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                Libro nuovo = new Libro(
                        titoloField.getText(),
                        autoreField.getText(),
                        isbnField.getText(),
                        genereField.getText(),
                        (Integer) valutazioneBox.getSelectedItem(),
                        (StatoLettura) statoBox.getSelectedItem()
                );
                try {
                    LibreriaManager.getInstance().addLibro(nuovo);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });add(aggiungi, BorderLayout.SOUTH);

        aggiornaLista(manager.getListaLibri());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setVisible(true);
    }

    public void aggiornaLista(ArrayList<Libro> libri) {
        tabella.setLibri(libri);
    }


    @Override
    public void update(java.util.Observable o, Object arg) {
        try {
            aggiornaLista(LibreriaManager.getInstance().getListaLibri());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
