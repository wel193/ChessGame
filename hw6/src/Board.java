public class Board {
    private Piece[][] board = new Piece[8][8];
    private Player white;
    private Player black;

    public Board(){
        this.white = new Player(ChessColor.white);
        this.black = new Player(ChessColor.black);
        this.setBoard(ChessColor.white);
        this.setBoard(ChessColor.black);
    }

    public void setBoard(ChessColor color){
        int firstRow = (color == ChessColor.white) ? 0 : 7;

        board[firstRow][0] = new Rook(firstRow, 0, color); // 车
        board[firstRow][7] = new Rook(firstRow, 7, color);
        board[firstRow][1] = new Knight(firstRow, 1, color); // 马
        board[firstRow][6] = new Knight(firstRow, 6, color);
        board[firstRow][2] = new Bishop(firstRow, 2, color); // 象
        board[firstRow][5] = new Bishop(firstRow, 5, color);
        board[firstRow][4] = new Queen(firstRow, 4, color);

        // store King object also in player's instance variable
        King king = new King(firstRow, 3, color);
        board[firstRow][3] = king;
        if (color == ChessColor.white){
            this.white.setKing(king);
        } else {
            this.black.setKing(king);
        }

        // 8 pawns
        int secondRow = (firstRow == 0) ? 1 : 6;
        for (int i = 0; i < 8; i++){
            board[secondRow][i] = new Pawn(secondRow, i, color);
        }
    }

    public boolean gameEnd(){
        return white.win() || black.win();
    }

    public ChessColor winner(){
        if (white.win()){
            return ChessColor.white;
        }
        return ChessColor.black;
    }

    public boolean occupied(int row, int col){
        return board[row][col] != null;
    }

    public void makeAvailable(int row, int col){
        board[row][col] = null;
    }

    public void placePiece(Piece movingPiece, int destRow, int destCol){
        board[destRow][destCol] = movingPiece;
    }

    public void killPiece(int row, int col){
        board[row][col].killed();
        makeAvailable(row, col);
    }

    public Piece getPiece(int row, int col){
        return board[row][col];
    }


    public void moveChessOnBoard(int startRow, int startCol, int destRow, int destCol){
        Piece movingPiece = this.getPiece(startRow, startCol);
        if (this.occupied(destRow, destCol)){
            this.killPiece(destRow, destCol);
        }
        this.placePiece(movingPiece, destRow, destCol);
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
