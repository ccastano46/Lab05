package domain;

public class Conecta4 {
    private char[][] board;
    private boolean player;

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
        char ficha;
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
        return someWinner();
    }

    public boolean someWinner(){
        return false;
    }
}
