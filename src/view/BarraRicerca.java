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
import java.io.IOException;
import java.util.ArrayList;

public class BarraRicerca extends JPanel {
    private final JTextField campoTitolo;
    private final JTextField campoGenere;
    private final JComboBox<StatoLettura> comboStato;
    private final JButton filtraBtn;
    private final MainUI mainUI; // riferimento per aggiornare la lista

    public BarraRicerca(MainUI mainUI) {
        this.mainUI = mainUI;

        campoTitolo = new JTextField(10);
        campoGenere = new JTextField(10);
        comboStato = new JComboBox<>(StatoLettura.values());
        filtraBtn = new JButton("Filtra");

        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(new JLabel("Titolo:"));
        add(campoTitolo);
        add(new JLabel("Genere:"));
        add(campoGenere);
        add(new JLabel("Stato:"));
        add(comboStato);
        add(filtraBtn);

        filtraBtn.addActionListener(e -> {
            try {
                filtra();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
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
}

