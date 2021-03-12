/**
 * This represents a Board class with a 2D array board and two Player objects.
 */
public class Board {
    private Piece[][] board;
    private boolean blackKingAlive = true;
    private boolean whiteKingAlive = true;

    /**
     * Constructor of Board object and initialized it to a start mode.
     *
     */
    public Board() {
        this.board = new Piece[8][8];
        this.setBoard(ChessColor.WHITE);
        this.setBoard(ChessColor.BLACK);
    }

    /**
     * Method to set the Board to the start mode. Initialized all Piece at the certain position.
     * @param color ChessColor
     *
     */
    public void setBoard(ChessColor color) {
        try{
            //initialize pieces on the first row
            int firstRow = (color == ChessColor.WHITE) ? 0 : 7;
            board[firstRow][0] = new Rook(firstRow, 0, color);
            board[firstRow][7] = new Rook(firstRow, 7, color);
            board[firstRow][1] = new Knight(firstRow, 1, color);
            board[firstRow][6] = new Knight(firstRow, 6, color);
            board[firstRow][2] = new Bishop(firstRow, 2, color);
            board[firstRow][5] = new Bishop(firstRow, 5, color);
            board[firstRow][4] = new Queen(firstRow, 4, color);
            board[firstRow][3] = new King(firstRow, 3, color);

            // initialize the paws on the second row.
            int secondRow = (firstRow == 0) ? 1 : 6;
            for (int i = 0; i < 8; i++){
                board[secondRow][i] = new Pawn(secondRow, i, color);
            }
        }
        catch (IllegalPieceException e){
            System.out.println("Fetal Error: Cannot initialize piece on board");
            System.out.println(e.getMessage());
        }

    }

    /**
     * Method to check if the black king still alive
     * @return boolean
     */
    public boolean isBlackKingAlive() {
        return blackKingAlive;
    }

    /**
     * Method to check if the white king still alive
     */
    public boolean isWhiteKingAlive(){
        return whiteKingAlive;
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
    public void placePiece(Piece movingPiece, int destRow, int destCol) {
        board[destRow][destCol] = movingPiece;
        try {
            movingPiece.setRow(destRow);
            movingPiece.setColumn(destCol);
        }catch(IllegalPieceException e){
            System.out.println(e.getMessage());
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
     * Method to check if the piece at the starting position can move to the destination position
     * @param list the coordinate input by user
     * @return boolean
     */
    public boolean canMoveChess(Integer[] list){
        Piece movingPiece = this.getPiece(list[0], list[1]);
        Piece targetPiece = this.getPiece(list[2], list[3]);
        //Check if the moving piece has blocked path
        if (!(movingPiece instanceof Knight) && !this.pathAvailable(list[0], list[1], list[2], list[3])){
            return false;
        }
        //return ture if there has target piece at destination coordinate and the moving piece can do the kill move.
        if (targetPiece != null && movingPiece.canKill(targetPiece)) {
            return true;
        }
        //return ture if there has no piece at destination coordinate and the moving piece can make the move.
        if (targetPiece == null && movingPiece.canMove(list[2], list[3])){
            return true;
        }
        //Otherwise, the move will not be valid.
        return false;
    }


    /**
     * Method to move the chess piece on board
     * @param startRow start row value
     * @param startCol start column value
     * @param destRow destination row value
     * @param destCol destination column value
     */
    public void moveChessOnBoard(int startRow, int startCol, int destRow, int destCol){
        System.out.format("Moving %s from (%d, %d) to (%d, %d) \n", this.getPiece(startRow, startCol), startRow, startCol, destRow, destCol);
        Piece movingPiece = this.getPiece(startRow, startCol);
        Piece targetPiece =  this.getPiece(destRow, destCol);
        if (this.occupied(destRow, destCol)){
            System.out.format("%s takes %s \n", this.getPiece(startRow, startCol), this.getPiece(destRow, destCol));
            this.makeAvailable(destRow, destCol);
        }
        this.placePiece(movingPiece, destRow, destCol);
        this.makeAvailable(startRow, startCol);
        if (targetPiece instanceof King){
            if (targetPiece.getColor() == ChessColor.BLACK){
                blackKingAlive = false;
            }
            else{
                whiteKingAlive = false;
            }
        }
    }

    /**
     * Method to get the increment of position difference. Supplement method of PathAvailable
     * @param a start row or column value
     * @param b destination row or column value
     * @return integer of increment value
     */
    public int getIncrement(int a, int b){
        return Integer.compare(b, a);
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
        while (startRow != destRow || startCol != destCol){
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

}
