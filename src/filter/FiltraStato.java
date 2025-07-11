package filter;

import model.Libro;
import model.StatoLettura;

public class FiltraStato implements FiltroLibro {
    private StatoLettura stato;

    public FiltraStato(StatoLettura stato) {
        this.stato = stato;
    }


    @Override
    public boolean filter(Libro libro) {
        return libro.getStato().equals(stato);
    }


}
