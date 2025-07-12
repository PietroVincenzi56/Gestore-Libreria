package filter;

import model.Libro;

//design pattern strategy
public interface FiltroLibro {
    boolean filter(Libro libro);


}
