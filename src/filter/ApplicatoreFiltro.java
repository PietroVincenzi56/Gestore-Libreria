package filter;

import model.Libro;

import java.util.ArrayList;
import java.util.List;

public class ApplicatoreFiltro {

    public static ArrayList<Libro> applicaFiltro(ArrayList<Libro> libri, FiltroLibro filtro) {
        ArrayList<Libro> ret = new ArrayList<>();
        for (Libro libro : libri) {
            if (filtro.filter(libro)) {
                ret.add(libro);
            }
        }
        return ret;
    }

}
