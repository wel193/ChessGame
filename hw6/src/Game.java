public class Game {
    private Player white;
    private Player black;
    private Board board;

    public Game(){
        board = new Board();
        white = new Player(ChessColor.white);
        black = new Player(ChessColor.black);
    }

    public Direction getDirection(int startRow, int startCol, int destRow, int destCol){
        if (destCol - startCol == 0){
            return Direction.vertical;
        }
        if (destRow - startRow == 0){
            return Direction.horizontal;
        }

        int slope = (destCol - startCol) / (destRow - startRow);
        switch (slope){
            case 1:
                return Direction.antiDiagonal;
            case -1:
                return Direction.mainDiagonal;
            default:
                return Direction.lPattern;
        }
    }

    public boolean pathAvailable(int startRow, int startCol, int destRow, int destCol){
        Direction direction = getDirection(startRow, startCol, destRow, destCol);
        switch (direction){
            case horizontal:
            case vertical:
                //return board.linePathAvailable(startRow, startCol, destRow, destCol, direction);
            case mainDiagonal:
            case antiDiagonal:
                //return board.diagonalPathAvailable(startRow, startCol, destRow, destCol, direction);
            default:
                return true;
        }
    }

    public boolean inBoardRange(int row, int col){
        return row < 0 || col < 0 || row > 7 || col > 7;
    }

    public void moveChess(int startRow, int startCol, int destRow, int destCol) throws IllegalArgumentException{
        if (!pathAvailable(startRow, startCol, destRow, destCol)){
            throw new IllegalArgumentException("The path is not available");
        }
        ChessPiece piece = board.getPiece(startRow, startCol);
        if (!board.occupied(destRow, destCol) && piece.canMove(destRow, destCol)){

    }
    }
}
