/**
 * This represents a Board class with a 2D array board and two Player objects.
 */
public class Board {
    private Piece[][] board = new Piece[8][8];
    private Player white;
    private Player black;

    /**
     * Constructor of Board object and initialized it to a start mode.
     */
    public Board(){
        this.white = new Player(ChessColor.white);
        this.black = new Player(ChessColor.black);
        this.setBoard(ChessColor.white);
        this.setBoard(ChessColor.black);
    }

    /**
     * Method to set the Board to the start mode. Initialized all Piece at the certain position.
     * @param color ChessColor
     */
    public void setBoard(ChessColor color){
        int firstRow = (color == ChessColor.white) ? 0 : 7;

        board[firstRow][0] = new Rook(firstRow, 0, color);
        board[firstRow][7] = new Rook(firstRow, 7, color);
        board[firstRow][1] = new Knight(firstRow, 1, color);
        board[firstRow][6] = new Knight(firstRow, 6, color);
        board[firstRow][2] = new Bishop(firstRow, 2, color);
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

    /**
     * Method to check if one of the Player is lose.
     * @return boolean
     */
    public boolean gameEnd(){
        return white.lose() || black.lose();
    }

    /**
     * Method to get the winner ChessColor.
     * @return ChessColor of the winner
     */
    public ChessColor winner(){
        if (!gameEnd()){
            return null;
        }
        if (white.lose()){
            return ChessColor.black;
        }
        return ChessColor.white;
    }

    /**
     * Method to check if certain position in row, column has piece.
     * @param row row value
     * @param col column value
     * @return boolean
     */
    public boolean occupied(int row, int col){
        return board[row][col] != null;
    }

    /**
     * Method to make the certain position available.
     * @param row row value
     * @param col column value
     */
    public void makeAvailable(int row, int col){
        board[row][col] = null;
    }

    /**
     * Method to move the Piece to destination position
     * @param movingPiece the Piece object
     * @param destRow the destination row
     * @param destCol the destination col
     */
    public void placePiece(Piece movingPiece, int destRow, int destCol){
        board[destRow][destCol] = movingPiece;
        movingPiece.setRow(destRow);
        movingPiece.setColumn(destCol);
    }

    /**
     * Method to kill the piece at certain position
     * @param row  row value
     * @param col  column value
     */
    public void killPiece(int row, int col){
        if (this.occupied(row, col)) {
            board[row][col].killed();
            makeAvailable(row, col);
        }
    }

    /**
     * Method to get the piece at certain position
     * @param row the row value
     * @param col the column value
     * @return Piece object
     */
    public Piece getPiece(int row, int col){
        return board[row][col];
    }

    /**
     * Method to move the chess piece on board
     * @param startRow start row value
     * @param startCol start column value
     * @param destRow destination row value
     * @param destCol destination column value
     */
    public void moveChessOnBoard(int startRow, int startCol, int destRow, int destCol){
        Piece movingPiece = this.getPiece(startRow, startCol);
        if (this.occupied(destRow, destCol)){
            System.out.format("%s takes %s \n", this.getPiece(startRow, startCol), this.getPiece(destRow, destCol));
            this.killPiece(destRow, destCol);
        }
        this.placePiece(movingPiece, destRow, destCol);
        this.makeAvailable(startRow, startCol);
    }

    /**
     * Method to get the increment of position difference. Supplement method of PathAvailable
     * @param a start row or column value
     * @param b destination row or column value
     * @return integer of increment value
     */
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

    /**
     * Method to check if certain path has block pieces.
     * @param startRow start row value
     * @param startCol start column value
     * @param destRow destination row value
     * @param destCol destination column value
     * @return boolean
     */
    public boolean pathAvailable(int startRow, int startCol, int destRow, int destCol){
        int rowIncrement = getIncrement(startRow, destRow);
        int colIncrement = getIncrement(startCol, destCol);
        destRow -= rowIncrement;
        destCol -= colIncrement;
        while (startRow != destRow || startCol != destRow){
            startRow += rowIncrement;
            startCol += colIncrement;
            if (this.occupied(startRow, startCol)){
                System.out.format("%s blocks on path! \n", this.getPiece(startRow, startCol));
                return false;
            }
        }
        return true;
    }

    /**
     * Method to print out the current board status
     */
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
        System.out.println( );
    }

    public static void main(String[] arg){
        Board b = new Board();
        Piece k = b.getPiece(0, 3);
        b.printBoard();
        b.moveChessOnBoard(0, 3, 7, 3 );
        System.out.println(b.gameEnd());
        System.out.println(b.winner());
    }
}
