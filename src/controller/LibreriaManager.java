package controller;

import model.Libreria;
import model.Libro;
import persistance.LibreriaDAO;
import sun.jvm.hotspot.utilities.Observable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibreriaManager extends Observable {
    private final Libreria libreria;
    private final LibreriaDAO dao;

    public LibreriaManager(Libreria libreria, LibreriaDAO dao) {
        this.libreria = libreria;
        this.dao = dao;
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

    public boolean modificaLibro(Libro libro) throws IOException {
        libreria.modificaLibro(libro);
        salva();
        setChanged();
        notifyObservers();
        return true;
    }

    public ArrayList<Libro> getiListaLibri() throws IOException {
        return libreria.getLibri();
    }

}
