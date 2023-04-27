package domain;
public class Conecta4 {
    private char[][] board;
    private boolean player;
    private char ficha;
    public Conecta4(int rows, int columns) throws Conecta4Exception {
        if (rows < 4 || columns < 4)
            throw new Conecta4Exception(Conecta4Exception.WRONG_RANGE);
        board = new char[rows][columns];
        player = true;
    }

    public char[][] board() {
        return board;
    }

    public boolean player(){
        return player;
    }

    public boolean play(int column) throws Conecta4Exception{
        if(column==board[0].length) throw new Conecta4Exception(Conecta4Exception.NO_COLUMN);
        if (player) {
            ficha = 'a';
        } else {
            ficha = 'r';
        }
        int i;
        for (i = board.length - 1 ; i >= 0; i--) {
            if(board[i][column] == '\u0000'){
                board[i][column]=ficha;
                break;
            } 
        }
        //System.out.println(i);
        if(i<0) throw new Conecta4Exception(Conecta4Exception.COLUMN_FULL);
        if(player){
            player=false;
        }else{
            player=true;
        }
        return someWinner(i, column);
    }

    /**
     * Metodo que revisa, si luego de colocar una ficha uno de los jugadores ha ganado
     * @return, dato que indica si el jugador en turno gano la partida.
     */
    private boolean someWinner(int row, int column){
        if(checkVertical(row, column)) return true;
        else if(checkHorizontal('+', row, column)) return true;
        else if(checkHorizontal('-', row, column)) return true;
        else if(checkDiagonal('-', row, column)) return true;
        else if(checkDiagonal('+', row, column)) return true;
        else return false;

    }
    private boolean checkVertical(int row, int column){
        if(!(row + 3 >= board.length)){
            for(int i= 1;i < 4; i++){
                if(board[row + i][column] != ficha) return false;
            }
            return true;
        }
        return false;
        
    }
    private boolean checkHorizontal(char direction,int row, int column){
        if(direction == '+' && !(column + 3 >= board[0].length)){
            for(int i= 1;i < 4; i++){
                if(board[row][column + i] != ficha) return false;
            }
            return true;
        }
        if(direction == '-' && !(column - 3 < 0)){
            for(int i= 1;i < 4; i++){
                if(board[row][column - i] != ficha) return false;
            }
            return true;
        }
        return false;
    }
    private boolean checkDiagonal(char direction,int row, int column){
        if(direction == '+' && !((column + 3 >= board[0].length) || (row + 3 >= board.length))){
            for(int i= 1;i < 4; i++){
                if(board[row + i][column + i] != ficha) return false;
            }
            return true;
        }
        if(direction == '-' && !((column - 3 < 0) || (row + 3 >= board.length))){
            for(int i= 1;i < 4; i++){
                if(board[row + i][column - i] != ficha) return false;
            }
            return true;
        }
        return false;
    }
}
