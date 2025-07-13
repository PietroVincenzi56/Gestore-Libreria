package view;

import controller.LibreriaManager;
import model.Libro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.TableCellRenderer;



public class ListaLibri extends JPanel {

    private JTable tabella;
    private DefaultTableModel model;
    private List<Libro> libri= new ArrayList<Libro>();

    public ListaLibri() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Titolo", "Autore", "Genere", "Stato", "Valutazione", "Modifica", "Elimina"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5 || column == 6; // Solo Modifica e Rimuovi sono cliccabili
            }
        };

        tabella = new JTable(model);
        tabella.setRowHeight(30);

        // Renderer per i pulsanti
        tabella.getColumn("Modifica").setCellRenderer(new ButtonRenderer());
        tabella.getColumn("Elimina").setCellRenderer(new ButtonRenderer());

        // Listener click su pulsanti
        tabella.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int riga = tabella.rowAtPoint(evt.getPoint());
                int colonna = tabella.columnAtPoint(evt.getPoint());
                if (riga >= 0 && colonna >= 5) {
                    Libro selezionato = libri.get(riga);
                    if (colonna == 5) {
                        try {
                            modificaLibro(selezionato);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (colonna == 6) {
                        try {
                            rimuoviLibro(selezionato);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        add(new JScrollPane(tabella), BorderLayout.CENTER);
    }

    public void setLibri(List<Libro> nuoviLibri) {
        libri = new ArrayList<>(nuoviLibri);
        model.setRowCount(0);
        for (Libro libro : libri) {
            model.addRow(new Object[]{
                    libro.getTitolo(),
                    libro.getAutore(),
                    libro.getGenere(),
                    libro.getStato() != null ? libro.getStato().toString() : "N/D",
                    libro.getScore() > 0 ? "⭐".repeat(libro.getScore()) : "N/D",
                    "Modifica",
                    "Elimina"
            });
        }
    }

    private void modificaLibro(Libro libroVecchio) throws IOException {
        DialogLibro dialog = new DialogLibro((JFrame) SwingUtilities.getWindowAncestor(this), libroVecchio);
        Libro modificato = dialog.mostra();
        if (modificato != null) {
            LibreriaManager.getInstance().modificaLibro(libroVecchio, modificato);
        }
    }

    private void rimuoviLibro(Libro libro) throws IOException {
        int conferma = JOptionPane.showConfirmDialog(this,
                "Vuoi davvero rimuovere \"" + libro.getTitolo() + "\"?",
                "Conferma rimozione",
                JOptionPane.YES_NO_OPTION);
        if (conferma == JOptionPane.YES_OPTION) {
            LibreriaManager.getInstance().removeLibro(libro);
        }
    }

    // Bottone visivo nella cella
    private static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }
}