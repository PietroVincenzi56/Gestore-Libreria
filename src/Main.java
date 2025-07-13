
import controller.LibreriaManager;
import model.Libreria;
import model.Libro;
import persistance.LibreriaDAO;
import persistance.LibreriaJsonDAO;
import view.MainUI;
import java.util.Observable;
import java.util.Observer;


import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args)  {
        // Percorso file della libreria JSON
        String path = "libreria.json";

        // DAO + libreria vuota
        LibreriaDAO dao = new LibreriaJsonDAO(path);
        Libreria libreria = new Libreria();

        try {
            // Carica i libri salvati (se esistono)
            libreria.setLibri(dao.caricaLibreria());
        } catch (IOException e) {
            System.err.println("Nessun file trovato, inizializzo libreria vuota.");
        }

        // Inizializza il singleton del manager
        LibreriaManager.init(libreria, dao);

        // Avvia la UI su thread EDT
        SwingUtilities.invokeLater(() -> {
            try {
                new MainUI(LibreriaManager.getInstance());
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Errore durante l'avvio dell'applicazione.");
            }
        });


    }
}
