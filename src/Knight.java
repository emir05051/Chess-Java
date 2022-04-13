public class Knight extends ChessPiece{
    public Knight(int x, int y) {
        super(x, y);
    }
    public String print() {
        if(isWhite) {
            return "♞";
        }
        else {
            return "♘";
        }
    }

    private boolean isValidMove(int x, int y, ChessPiece[][] board) {
        int oldX = this.fieldX;
        int oldY = this.fieldY;

        int differenceX = Math.abs(x - oldX);
        int differenceY = Math.abs(y - oldY);

        boolean checkMoveHorizontal = differenceX == 1 && differenceY == 2;
        boolean checkMoveVertical = differenceX == 2 && differenceY == 1;

        if(isAllyPiece(x, y, board))
            return false;
        else if(!(checkMoveVertical || checkMoveHorizontal))
            return false;


        //  Если конь на D5, то :
        //  E7 C7 C3 E3 => D + 1 || D - 1 && 5 + 2 || 5 - 2
        //  B6 B4 F6 F4 => D + 2 || d - 2 && 5 + 1 || 5 - 1

        return true;
    }
    public void move(int x, int y, ChessPiece[][] board) {
        if(isValidMove(x, y, board)) {
            moveFigure(x, y, board);
        }
        else {
            this.isChanged = false;
            System.out.println("Error");
        }
    }
}
