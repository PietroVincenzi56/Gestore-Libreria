package filter;

import model.Libro;

public class FiltroAutore implements FiltroLibro {
   private String autore;

    public FiltroAutore(String autore) {
        this.autore = autore;
    }

    @Override
    public boolean filter(Libro libro) {
        return libro.getAutore() != null && libro.getAutore().toLowerCase().contains(autore.toLowerCase());
    }
}

