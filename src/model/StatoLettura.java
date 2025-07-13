package model;

public enum StatoLettura {
    LETTO("Letto"),
    DA_LEGGERE("Da leggere"),
    IN_LETTURA("In lettura");

    private final String label;

    StatoLettura(String label) {
        this.label = label;
    }
    @Override
    public String toString() {
        return switch (this) {
            case DA_LEGGERE -> "Non letto";
            case IN_LETTURA -> "In lettura";
            case LETTO -> "Letto";
        };
    }
}
