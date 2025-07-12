package model;

import java.util.ArrayList;
import java.util.List;

public class Libreria {
    private ArrayList<Libro> libri = new ArrayList<>();

    public void addLibro(Libro l) { //Poi da fare a mestiere
        libri.add(l);
    }

    public void removeLibro(Libro l) {
        libri.remove(l);
    }

    public Libro getLibro(Libro l) {
        for (Libro i : libri){
            if (i.equals(l))
                return i;
        }
        return null;
    }

    public boolean modificaLibro(Libro l) {
        for (Libro i : libri) {
            if (i.equals(l)) {
                removeLibro(i);
                addLibro(l);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Libro> getLibri() {
        ArrayList<Libro> ret = new ArrayList<>();
        for (Libro i : libri) {
            ret.add(new Libro(i));
        }
        return ret;
    }

    public void setLibri(List<Libro> caricata){
        this.libri.clear();
        for (Libro i : caricata) {
            libri.add(i);
        }
    }



}
