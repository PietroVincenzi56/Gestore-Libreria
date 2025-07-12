package filter;

import model.Libro;

public class FiltroTitolo implements FiltroLibro {
    private String titolo;

    public FiltroTitolo(String titolo) {
        this.titolo = titolo;
    }

    @Override
    public boolean filter(Libro libro) {
        return libro.getTitolo() != null && libro.getTitolo().toLowerCase().contains(titolo);
    } //contains funziona anche con solo un pezzo

}
