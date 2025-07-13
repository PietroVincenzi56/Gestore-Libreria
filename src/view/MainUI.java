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
            DialogLibro dialog = new DialogLibro(this);  // passa 'null' implicitamente
            Libro nuovo = dialog.mostra();

            if (nuovo != null) {
                try {
                    LibreriaManager.getInstance().addLibro(nuovo);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Errore durante il salvataggio del libro.", "Errore", JOptionPane.ERROR_MESSAGE);
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
