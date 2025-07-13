package model;

public class Libro {
    private String titolo;
    private String autore;
    private String isbn;
    private String genere;
    private int score;
    private StatoLettura stato;

    public Libro(String titolo, String autore, String isbn, String genere, int score, StatoLettura stato) {
        this.titolo = titolo;
        this.autore = autore;
        this.isbn = isbn;
        this.genere = genere;
        this.score = score;
        this.stato = stato;
    }

    public Libro() {}


    public Libro(Libro l) {
        this.genere = l.getGenere();
        this.isbn = l.getIsbn();
        this.autore = l.getAutore();
        this.titolo = l.getTitolo();
        this.score = l.getScore();
        this.stato = l.getStato();

    }


    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public StatoLettura getStato() {
        return stato;
    }

    public void setStato(StatoLettura stato) {
        this.stato = stato;
    }


    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Libro libro = (Libro) obj;
        return isbn != null && isbn.equals(libro.isbn);
    }

    @Override
    public int hashCode() {
        return isbn != null ? isbn.hashCode() : 0;
    }

}
