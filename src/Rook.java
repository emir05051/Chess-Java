public class Rook extends ChessPiece{
    public Rook(int x, int y) {
        super(x, y);
    }
    public String print() {
        if(isWhite) {
            return "♜";
        }
        else {
            return "♖";
        }
    }
    private boolean isValidMove(int x, int y, ChessPiece[][] board) {
        int oldX = this.fieldX;
        int oldY = this.fieldY;

        int differenceX = Math.abs(x - oldX);
        int differenceY = Math.abs(y - oldY);

        boolean checkMoveHorizontal = differenceX == 0;
        boolean checkMoveVertical = differenceY == 0;

        if(isAllyPiece(x, y, board))
            return false;
        else if(!(checkMoveVertical || checkMoveHorizontal))
            return false;

        if(checkMoveVertical) {
            checkLine(x, oldX, y, oldY, board);
        }
        else{
            checkLine(x, oldX, y, oldY,  board);
        }

        return true;
    }

    private boolean checkLine(int x, int oldX, int y, int oldY, ChessPiece[][] board) {

        while (oldX != x || oldY != y) {

            oldX = (oldX != x)
                    ? oldX > x
                        ? oldX - 1
                        : oldX + 1
                    : oldX;

            oldY = (oldY != y)
                    ? oldY > y
                        ? oldY - 1
                        : oldY + 1
                    : oldY;

            if((oldX == x || oldY == y) && !isAllyPiece(oldX, oldY, board)) {
                return true;
            }

            if(!isField(oldX, oldY, board)) {
                return false;
            }
        }
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
