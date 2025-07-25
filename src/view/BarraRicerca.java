package view;

import controller.LibreriaManager;
import filter.ApplicatoreFiltro;
import filter.FiltraStato;
import filter.FiltroGenere;
import filter.FiltroTitolo;
import model.Libro;
import model.StatoLettura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.ArrayList;

public class BarraRicerca extends JPanel {
    private final JTextField campoTitolo;
    private final JTextField campoGenere;
    private final JComboBox<StatoLettura> comboStato;
    private final JButton filtraBtn;
    private final JButton annullaBtn;
    private final MainUI mainUI; // riferimento per aggiornare la lista
    private final JComboBox<String> comboOrdina;



    public BarraRicerca(MainUI mainUI) {
        this.mainUI = mainUI;

        campoTitolo = new JTextField(10);
        campoGenere = new JTextField(10);
        comboStato = new JComboBox<>(StatoLettura.values());
        filtraBtn = new JButton("Filtra");
        annullaBtn = new JButton("Annulla");

        comboOrdina = new JComboBox<>(new String[]{
                "Nessun ordinamento",
                "Titolo (A-Z)",
                "Autore (A-Z)",
                "Valutazione (Alta → Bassa)",
                "Stato lettura"
        });


        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(new JLabel("Titolo:"));
        add(campoTitolo);
        add(new JLabel("Genere:"));
        add(campoGenere);
        add(new JLabel("Stato:"));
        add(comboStato);
        add(new JLabel("Ordina per:"));
        add(comboOrdina);
        add(filtraBtn);
        add(annullaBtn);

        comboOrdina.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                try {
                    ordina((String) comboOrdina.getSelectedItem());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        filtraBtn.addActionListener(e -> {
            try {
                filtra();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        annullaBtn.addActionListener(e -> {
            try {
                annulla();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });



    }

    private void ordina(String selectedItem) throws IOException {
        ArrayList<Libro> libri = new ArrayList<>(LibreriaManager.getInstance().getListaLibri());

        String ordinamento = (String) comboOrdina.getSelectedItem();
        if (ordinamento != null) {
            switch (ordinamento) {
                case "Titolo (A-Z)" -> libri.sort((l1, l2) -> l1.getTitolo().compareToIgnoreCase(l2.getTitolo()));
                case "Autore (A-Z)" -> libri.sort((l1, l2) -> l1.getAutore().compareToIgnoreCase(l2.getAutore()));
                case "Valutazione (Alta → Bassa)" -> libri.sort((l1, l2) -> Integer.compare(l2.getScore(), l1.getScore()));
                case "Stato lettura" -> libri.sort((l1, l2) -> l1.getStato().compareTo(l2.getStato()));
            }
        }
        mainUI.aggiornaLista(libri);
    }

    private void filtra() throws IOException {
        ArrayList<Libro> libri = new ArrayList<>(LibreriaManager.getInstance().getListaLibri());

        String titolo = campoTitolo.getText().trim();
        String genere = campoGenere.getText().trim();
        Object statoSelezionato = comboStato.getSelectedItem();

        if (!titolo.isEmpty()) {
            libri = ApplicatoreFiltro.applicaFiltro(libri, new FiltroTitolo(titolo));
        }
        if (!genere.isEmpty()) {
            libri = ApplicatoreFiltro.applicaFiltro(libri, new FiltroGenere(genere));
        }
        if (statoSelezionato instanceof StatoLettura stato) {
            libri = ApplicatoreFiltro.applicaFiltro(libri, new FiltraStato(stato));
        }

        mainUI.aggiornaLista(libri);
    }

    private void annulla() throws IOException {
            ArrayList<Libro> tutti = new ArrayList<>(LibreriaManager.getInstance().getListaLibri());
            mainUI.aggiornaLista(tutti);

            // Pulizia campi di ricerca
            campoTitolo.setText("");
            campoGenere.setText("");
            comboStato.setSelectedIndex(0);
        }

}

