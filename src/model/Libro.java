package model;

public class Libro {
    private String titolo;
    private String autore;
    private String isbn;
    private String genere;
    private int score;
    private StatoLettura stato;


    public Libro(String genere, String isbn, String autore, String titolo) {
        this.genere = genere;
        this.isbn = isbn;
        this.autore = autore;
        this.titolo = titolo;
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

}
