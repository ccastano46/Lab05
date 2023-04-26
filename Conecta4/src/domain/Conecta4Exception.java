package domain;

public class Conecta4Exception extends Exception {
    public static final String COLOR_EXISTENTE = "Alguno de los jugadores ya tiene ese color";

    Conecta4Exception(String message) {
        super(message);
    }
}
