public class Pawn extends ChessPiece{
    boolean isFirstMove = true;

    public Pawn(int x, int y) {
        super(x, y);
    }

    public String print() {
        if(isWhite) {
            return "♟";
        }
        else {
            return "♙";
        }
    }

    private boolean isValidMove(int x, int y, ChessPiece[][] board) {
        int oldX = this.fieldX;
        int oldY = this.fieldY;

        int differenceX = Math.abs(x - oldX);
        int differenceY = Math.abs(y - oldY);

        boolean checkX = differenceX == 1 || differenceX == 2;
        boolean checkY = differenceY == 1 || differenceY == 0;

        if(!checkX && !checkY && isAllyPiece(x, y, board))
            return false;

        else if(differenceX == 2 && isFirstMove) {
            int temp = this.isWhite ? 1 : -1;
            return isField(x, y, board) && isField(x - temp, y, board);
        }

        else if(differenceX == 2)
            return false;


        if(differenceY == 0 && isField(x, y, board))
            return true;

        else if(differenceY == 1 && !(isField(x, y, board)))
            return true;

        return false;
    }

    public void move(int x, int y, ChessPiece[][] board){

        if(isValidMove(x, y, board)) {
            moveFigure(x, y, board);
            this.isFirstMove = false;
        }
        else {
            this.isChanged = false;
            System.out.println("Incorrect move");
        }
    }
}
