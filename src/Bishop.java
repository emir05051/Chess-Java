public class Bishop extends ChessPiece{
    public Bishop(int x, int y) {
        super(x, y);
    }
    public String print() {
        if(isWhite) {
            return "♝";
        }
        else {
            return "♗";
        }
    }
    private boolean isValidMove(int x, int y, ChessPiece[][] board) {
        int oldX = this.fieldX;
        int oldY = this.fieldY;

        int differenceX = Math.abs(x - oldX);
        int differenceY = Math.abs(y - oldY);

        boolean checkMove =  differenceX == differenceY;

        if(!checkMove) {
            return false;
        }
        else if(checkDiagonal(x, oldX, y, oldY, board))
            return true;
        else
            return false;
    }

    private boolean checkDiagonal(int x, int oldX, int y, int oldY, ChessPiece[][] board) {
        while (x != oldX && oldY != y) {
            oldX = oldX > x ? oldX - 1 : oldX + 1;
            oldY = oldY > y ? oldY - 1 : oldY + 1;

            if(oldX == x && !isAllyPiece(x, y, board)) {
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
