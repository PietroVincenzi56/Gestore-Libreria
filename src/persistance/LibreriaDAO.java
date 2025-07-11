package persistance;

import model.Libreria;
import model.Libro;

import java.io.IOException;
import java.util.List;

public interface LibreriaDAO {
    List<Libro> caricaLibreria() throws IOException;
    void salvaLibreria(List<Libro> libreria) throws IOException;


}
