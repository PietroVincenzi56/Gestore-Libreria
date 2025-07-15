package filter;

import model.Libro;

public class FiltroGenere implements FiltroLibro {
    private String genere;

    public FiltroGenere(String genere) {
        this.genere = genere;
    }


    @Override
    public boolean filter(Libro libro) {
        return libro.getGenere() != null && libro.getGenere().toLowerCase().contains(genere.toLowerCase());
    }
}
