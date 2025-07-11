package persistance;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Libro;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibreriaJsonDAO implements LibreriaDAO {
    private final File file;
    private final ObjectMapper mapper = new ObjectMapper();

    public LibreriaJsonDAO(String filename) {
        this.file = new File(filename);
    }

    @Override
    public List<Libro> caricaLibreria() throws IOException {
        if (!file.exists()) return new ArrayList<>();
        return mapper.readValue(file, new TypeReference<List<Libro>>() {});
    }

    @Override
    public void salvaLibreria(List<Libro> libri) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, libri);
    }
}



