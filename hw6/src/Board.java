public class Board {
    private ChessPiece[][] board = new ChessPiece[8][8];

    public Board(){
        this.resetBoard();
    }

    public void resetBoard(){
        board[0][0] = new Rook(0, 0, ChessColor.white);
        board[0][1] = new Knight(0, 1, ChessColor.white);
        board[0][2] = new Bishop(0, 2, ChessColor.white);
        board[0][3] = new Queen(0, 3, ChessColor.white);
        board[0][4] = new King(0, 4, ChessColor.white);
        board[0][5] = new Bishop(0, 5, ChessColor.white);
        board[0][6] = new Knight(0, 6, ChessColor.white);
        board[0][7] = new Rook(0, 7, ChessColor.white);

        board[7][0] = new Rook(0, 0, ChessColor.black);
        board[7][1] = new Knight(0, 1, ChessColor.black);
        board[7][2] = new Bishop(0, 2, ChessColor.black);
        board[7][3] = new Queen(0, 3, ChessColor.black);
        board[7][4] = new King(0, 4, ChessColor.black);
        board[7][5] = new Bishop(0, 5, ChessColor.black);
        board[7][6] = new Knight(0, 6, ChessColor.black);
        board[7][7] = new Rook(0, 7, ChessColor.black);

        for(int i = 0; i < 8; i ++){
            board[1][i] = new Pawn(1, i, ChessColor.white);
            board[6][i] = new Pawn(6, i, ChessColor.black);
        }
    }


    public boolean occupied(int row, int col){
        return board[row][col] != null;
    }

    public ChessPiece getPiece(int row, int col){
        return board[row][col];
    }


    public void moveChessOnBoard(int startRow, int startCol, int destRow, int destCol){

    }

    public int getIncrement(int a, int b){
        if (a < b){
            return 1;
        }
        else if (a == b){
            return 0;
        }
        else{
            return -1;
        }
    }

    public boolean pathAvailable(int startRow, int startCol, int destRow, int destCol){
        int rowIncrement = getIncrement(startRow, destRow);
        int colIncrement = getIncrement(startCol, destCol);
        destRow -= rowIncrement;
        destCol -= colIncrement;
        while (startRow != destRow && startCol != destRow){
            startRow += rowIncrement;
            startCol += colIncrement;
            if (this.occupied(startRow, startCol)){
                System.out.format("%s is block on path!", this.getPiece(startRow, startCol));
                return false;
            }
        }
        return true;
    }

    public void printBoard(){
        for (int i = 7; i >= 0; i --){
            System.out.println("  -----------------------------------------------------------------------------------------------------------------------------------------");
            System.out.format("%d |  ", i);
            for (int j = 0; j < 8; j ++){
                if (!this.occupied(i, j)){
                    System.out.format("%-14s|  ", " ");
                }
                else{
                    System.out.format("%-14s|  ", board[i][j]);
                }
            }
            System.out.println( );
        }

        System.out.println("  -----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("       ");

        for (int i = 0; i < 8; i ++){
            System.out.format("  %-15d", i);
        }
    }

    public static void main(String[] arg){
        Board b = new Board();
        b.printBoard();
        System.out.println( );
        System.out.println(b.pathAvailable( 1,2,7,2));
    }
}
