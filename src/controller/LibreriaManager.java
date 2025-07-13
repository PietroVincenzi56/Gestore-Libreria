package controller;

import model.Libreria;
import model.Libro;
import persistance.LibreriaDAO;
import persistance.LibreriaJsonDAO;
import java.util.Observable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibreriaManager extends Observable {
    private final Libreria libreria;
    private final LibreriaDAO dao;
    private static LibreriaManager instance;


    private LibreriaManager(Libreria libreria, LibreriaDAO dao) {
        this.libreria = libreria;
        this.dao = dao;
    }

    public static void init(Libreria libreria, LibreriaDAO dao) {
        if (instance == null) {
            instance = new LibreriaManager(libreria, dao);
        }
    }

    public static LibreriaManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("LibreriaManager non inizializzato!");
        }
        return instance;
    }


    public void salva() throws IOException {
        dao.salvaLibreria(libreria.getLibri());
    }

    public void carica() throws IOException {
        List<Libro> caricati = dao.caricaLibreria();
        libreria.setLibri(caricati);
    }


    public boolean addLibro(Libro libro) throws IOException {
        libreria.addLibro(libro);
        salva();
        setChanged();
        notifyObservers();
        return true;
    }

    public boolean removeLibro(Libro libro) throws IOException {
        libreria.removeLibro(libro);
        salva();
        setChanged();
        notifyObservers();
        return true;
    }

    public boolean modificaLibro(Libro old, Libro neww) throws IOException {
        libreria.modificaLibro(old, neww);
        salva();
        setChanged();
        notifyObservers();
        return true;
    }

    public ArrayList<Libro> getListaLibri() throws IOException {
        return libreria.getLibri();
    }

}
