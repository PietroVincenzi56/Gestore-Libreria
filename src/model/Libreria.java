package model;

import java.util.ArrayList;

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

    //in base allo stato lettura
    public ArrayList<Libro> filtraLibri(StatoLettura s) {
        ArrayList<Libro> ret = new ArrayList<>();
            for (Libro i : libri) {
                if (i.getStato().equals(s))
                    ret.add(i);
            }
        return ret;
    }



}
