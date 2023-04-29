package src.domain;

public class Conecta4Exception extends Exception {
    public static final String WRONG_RANGE = "Rango Invalido";
    public static final String COLUMN_FULL = "Columna llena";
    public static final String BOARD_FULL = "Es un empate!";
    public static final String COLOR_EXISTENTE = "Alguno de los jugadores ya tiene ese color";
    public static final String NO_COLUMN = "Elige una columna";
   
    

    public Conecta4Exception(String message){
        super(message);
    }
}