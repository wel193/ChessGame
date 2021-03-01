import java.util.*;
import java.util.Scanner;

/**
 * This represents a Game class to initialize the game start on Board.
 */
public class Game {
    private Board board;

    /**
     * Constructor of Game object with a new Board object
     */
    public Game(){
        board = new Board();
    }

    /**
     * Method to check if certain position is in the range of board
     * @param row the row value
     * @param col the column value
     * @return boolean
     */
    public boolean notInRange(int row, int col){
        return row < 0 || col < 0 || row > 7 || col > 7;
    }

    /**
     * Method to move the piece at the starting position can move to the destination position
     * @param startRow start row value
     * @param startCol start column value
     * @param destRow destination row value
     * @param destCol destination column value
     */
    public void moveChess(int startRow, int startCol, int destRow, int destCol){
        System.out.format("Moving %s from (%d, %d) to (%d, %d) \n", board.getPiece(startRow, startCol), startRow, startCol, destRow, destCol);
        board.moveChessOnBoard(startRow, startCol, destRow, destCol);
    }

    /**
     * Method to check if the piece at the starting position can move to the destination position
     * @param list the coordinate input by user
     * @return boolean
     */
    public boolean canMoveChess(Integer[] list){
        //first check if both starting coordinate and destination coordinate is in the range of board
        if (notInRange(list[0], list[1]) || notInRange(list[2], list[3])) {
            System.out.println("This move is out of range");
            return false;
        }

        Piece movingPiece = board.getPiece(list[0], list[1]);
        Piece targetPiece = board.getPiece(list[2], list[3]);

        //Check if the starting position has piece
        if (movingPiece == null) {
            System.out.println("The moving piece is not selected");
            return false;
        }

        //Check if the moving piece has blocked path
        if (!(movingPiece instanceof Knight) && !board.pathAvailable(list[0], list[1], list[2], list[3])){
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
        System.out.println("This move is not valid");
        return false;
    }

    /**
     * Method to check if the user is selected to valid ChessColor piece.
     * @param startRow start row value
     * @param startCol start column value
     * @return boolean
     */
    public boolean isMovingWhite(int startRow, int startCol){
        return board.getPiece(startRow, startCol).getColor() == ChessColor.white;
    }

    /**
     * Method to check if the coordinate input by the user is valid
     * @param list the list of coordinate input
     * @param color ChessColor
     * @return boolean
     */
    public boolean getValidCoordinate(Integer[] list, ChessColor color) {
        //first check if the coordinate input has four number
        if (list == null || list.length != 4){
            System.out.println("The coordinate input in not valid");
            return false;
        }

        //Check if the selected moving piece matchs the turn
        if (color == ChessColor.white && !isMovingWhite(list[0], list[1])){
            System.out.println("You can only move WHITE chess");
            return false;
        }
        else if (color == ChessColor.black && isMovingWhite(list[0], list[1])){
            System.out.println("You can only move BLACK chess");
            return false;
        }

        //then check if the list input has valid move
        if (!canMoveChess(list)){
            return false;
        }

        return true;
    }

    /**
     * Method to initiated the turn of given ChessColor.
     * @param color the ChessColor
     */
    public void turn(ChessColor color){
        Integer[] inputList = null;
        board.printBoard();
        if (color == ChessColor.white){
            System.out.println("\n======> White turn");
        }
        else{
            System.out.println("\n======> Black turn");
        }
        while(!getValidCoordinate(inputList, color)){
            inputList = inputCoordinate();
        }
        moveChess(inputList[0], inputList[1], inputList[2], inputList[3]);

    }

    /**
     * Method to initiated the game of chess.
     */
    public void playChess(){
        System.out.println("Game start!");
        while (!board.gameEnd()) {

            //the white go first in the chess game
            this.turn(ChessColor.white);
            if (board.gameEnd()){
                break;
            }
            this.turn(ChessColor.black);
        }
        ChessColor color = board.winner();
        System.out.println("The winner is " + color);
    }

    /**
     * Method to input the coordinate by user.
     * @return integer list of coordinate: {start row, start column, end row, end column}
     */
    public Integer[] inputCoordinate(){
        Integer[] list = new Integer[4];
        System.out.println("Input coordinate (start row, start column, end row, end column): ");
        Scanner scan = new Scanner(System.in);
        list[0] = scan.nextInt();
        list[1] = scan.nextInt();
        list[2] = scan.nextInt();
        list[3] = scan.nextInt();
        return list;
    }

    /**
     * Main function to execute the game start.
     * @param arg entry point
     */
    public static void main(String[] arg){
        Game game = new Game();
        game.playChess();
    }
}
