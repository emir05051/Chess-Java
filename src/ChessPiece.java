public class ChessPiece {
    int fieldX;
    int fieldY;
    boolean isWhite;
    boolean isChanged = false;

    public ChessPiece(int x, int y) {
        this.fieldX = x;
        this.fieldY = y;
        if(x == 1 || x == 0) {
            this.isWhite = true;
        }
        else if (x == 6 || x == 7){
            this.isWhite = false;
        }
    }

    public String print(){
        return "";
    }

    public void moveFigure(int x, int y, ChessPiece[][] board) {
        board[x][y] = board[fieldX][fieldY];
        board[fieldX][fieldY] = new Field(fieldX, fieldY);
        this.fieldY = y;
        this.fieldX = x;
        this.isChanged = true;
    }

    public boolean isField (int x, int y, ChessPiece[][] board) {
        return board[x][y] instanceof Field;
    }


    public boolean isAllyPiece(int x, int y, ChessPiece[][] board) {
        if(!isField(x, y, board) && board[x][y].isWhite == this.isWhite) {
            return true;
        }
        else return false;
    }

    public void move(int x, int y, ChessPiece[][] chessBoard) {
    }

//    public void move(int x, int y, ChessPiece[][] board) {
//        if(isValidMove(x, y, board)) {
//            moveFigure(x, y, board);
//        }
//        else {
//            this.changed = false;
//            System.out.println("Error");
//        }
//    }
//  ??? Как сделать вызов этой функции в других классах детях ??

}
