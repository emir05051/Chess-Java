import java.util.Scanner;

public class ChessUI {
    static final ChessPiece[][] chessBoard = new ChessPiece[8][8];
    static Scanner sc = new Scanner(System.in);
    static final int max = 10;
    static boolean whiteMove = true;

    public static void main(String[] args) throws Exception {
        fillBoard();
        while (max > 0) {
            print();
            log("Move");
            log("Pick piece: ");
            String supposedPiece = sc.nextLine();
            ChessPiece piece;

            try {
                piece = getPiece(supposedPiece);
            } catch (Exception e) {
                log("Incorrect input");
                continue;
            }

            log("Make move");

            String move = sc.nextLine();

            try{
                makeMove(move, piece);
            }
            catch (Exception e) {
                log(e);
            }
        }
    }

    static void toggleMove() {
        whiteMove = !whiteMove;
    }
    static void makeMove(String move, ChessPiece piece)  throws Exception{
        int y = Converter.convertX(move.charAt(0));
        int x = parseInt(move);

        if(piece.isWhite && !whiteMove) {
            throw new Exception("Black move");
        }
        else if(!piece.isWhite && whiteMove) {
            throw new Exception("White move");
        }

        piece.move(x, y, chessBoard);

        if(piece.isChanged) {
            toggleMove();
        }
    }

    static <T> void log(T v) {
        System.out.println(v);
    }

    static ChessPiece getPiece(String place) throws Exception {
        Converter converter = new Converter();

        int y = converter.convertX(place.charAt(0));
        int x = parseInt(place);

        if(chessBoard[x][y] instanceof Field) {
            throw new Exception("You cannot move empty field");
        }

        return chessBoard[x][y];
    }


    static int parseInt(String s) {
        return Character.getNumericValue(s.charAt(1)) - 1;
    }

    //    Вывод
    private static void print() {
        if(whiteMove) printBoardWhite();
        else printBoardBlack();
    }

    private static void printBoardBlack() {
        String letters = " a  b  c d  e f  g h";
        System.out.printf(" %s", letters);
        System.out.println();

        for(int i = 0; i < 8; i++) {
            System.out.print(i+1 + " ");
            for(int j = 0; j < 8; j++) {
                System.out.print(chessBoard[i][j].print() + " ");
            }
            System.out.println();
        }
    }

    private static void printBoardWhite() {
        String letters = " a  b  c d  e f  g h";
        System.out.printf(" %s", letters);
        System.out.println();

        for(int i = 7; i >= 0; i--) {
            System.out.print(i+1 + " ");
            for(int j = 0; j < 8; j++) {
                System.out.print(chessBoard[i][j].print() + " ");
            }
            System.out.println();
        }
    }

//    Заполнение доски
    private static void fillBoard() {
        chessBoard[0] = fillPieces(0);
        chessBoard[1] =  fillPawns(1);

        chessBoard[6] = fillPawns(6);
        chessBoard[7] = fillPieces(7);

        for(int i = 2; i <= 5; i++) {
            chessBoard[i] = fillFields(i);
        }
    }
    private static ChessPiece[] fillFields(int x) {
        return new ChessPiece[]{
                new Field(x, 0), new Field(x,1), new Field(x, 2), new Field(x,3),
                new Field(x, 4), new Field(x,5), new Field(x, 6), new Field(x, 7)
        };
    }
    private static ChessPiece[] fillPawns(int x) {
        ChessPiece[] pieces = new ChessPiece[8];
        for(int i = 0; i < 8; i++) {
            pieces[i] = new Pawn(x, i);
        }
        return pieces;
    }
    private static ChessPiece[] fillPieces(int x) {
        return new ChessPiece[]{
                new Rook(x, 0), new Knight(x,1), new Bishop(x, 2), new Queen(x,3),
                new King(x, 4), new Bishop(x,5), new Knight(x, 6), new Rook(x, 7)
        };
    }

}
