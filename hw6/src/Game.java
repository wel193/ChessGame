import java.util.*;
import java.util.Scanner;

/**
 * This represents a Game class to initialize the game start on Board.
 */
public class Game {
    private Board board;

    /**
     * Constructor of Game object with a new Board object
     * @throws IllegalPieceException if the row or column has invalid value in the Board class
     */
    public Game() {
        board = new Board();
    }

    /**
     * Method to print out the winner
     */
    public void printOutWinner(){
        if (board.isBlackKingAlive()){
            System.out.println("Black wins!");
        }else{
            System.out.println("White wins!");
        }
    }

    /**
     * Method to check if one of the king being killed
     * @return
     */
    public boolean gameEnd(){
        return !board.isBlackKingAlive() || !board.isWhiteKingAlive();
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
     * Method to check if the user selectes the valid ChessColor piece.
     * @param startRow start row value
     * @param startCol start column value
     * @return boolean
     */
    public boolean isMovingWhite(int startRow, int startCol){
        return board.getPiece(startRow, startCol).getColor() == ChessColor.WHITE;
    }

    /**
     * Method to check if the coordinate input by the user is valid
     * @param list the list of coordinate input
     * @param color ChessColor
     * @return boolean
     * @throws IllegalCoordinateInput if the coordinate input is invalid
     */
    public boolean getValidCoordinate(Integer[] list, ChessColor color){
        // check if the coordinate is in range
        if (notInRange(list[0], list[1]) || notInRange(list[2], list[3])) {
            System.out.println("This move is out of range");
            return false;
        }

        //check if the starting coordinate is selected.
        Piece movingPiece = board.getPiece(list[0], list[1]);
        if (movingPiece == null) {
            System.out.println("The moving piece is not selected");
            return false;
        }
        //Check if the selected moving piece matches the turn
        if (color == ChessColor.WHITE && !isMovingWhite(list[0], list[1])){
            System.out.println("You can only move WHITE chess");
            return false;
        }
        else if (color == ChessColor.BLACK && isMovingWhite(list[0], list[1])){
           System.out.println("You can only move BLACK chess");
            return false;
        }

        //then check if the list input has valid move
        if (!board.canMoveChess(list)){
            System.out.println("This move is not valid");
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
        if (color == ChessColor.WHITE){
            System.out.println("\n======> White turn");
        }
        else{
            System.out.println("\n======> Black turn");
        }
        while(inputList == null || !getValidCoordinate(inputList, color)){
            inputList = inputCoordinate();
        }
        board.moveChessOnBoard(inputList[0], inputList[1], inputList[2], inputList[3]);

    }

    /**
     * Method to initiated the game of chess.
     */
    public void playChess() {
        System.out.println("Game start!");
        while (!this.gameEnd()) {

            //the white go first in the chess game
            this.turn(ChessColor.WHITE);
            if (this.gameEnd()){
                break;
            }
            this.turn(ChessColor.BLACK);
        }
        printOutWinner();
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
     *
     */
    public static void main(String[] arg) {
        Game game = new Game();
        game.playChess();

    }
}
