import java.util.*;
import java.util.Scanner;

public class Game {
    private Board board;

    public Game(){
        board = new Board();
    }

    public Direction getDirection(int startRow, int startCol, int destRow, int destCol){
        if (destCol - startCol == 0){
            return Direction.vertical;
        }
        if (destRow - startRow == 0){
            return Direction.horizontal;
        }

        int slope = (destCol - startCol) / (destRow - startRow);
        return switch (slope) {
            case 1 -> Direction.antiDiagonal;
            case -1 -> Direction.mainDiagonal;
            default -> Direction.lPattern;
        };
    }

    public boolean pathAvailable(int startRow, int startCol, int destRow, int destCol){
        Direction direction = getDirection(startRow, startCol, destRow, destCol);
        return switch (direction) {
            case horizontal, vertical, mainDiagonal, antiDiagonal -> board.pathAvailable(startRow, startCol, destRow, destCol);
            default -> true;
        };
    }

    public boolean notInRange(int row, int col){
        return row < 0 || col < 0 || row > 7 || col > 7;
    }

    public void moveChess(int startRow, int startCol, int destRow, int destCol){
        System.out.format("Moving %s from (%d, %d) to (%d, %d) \n", board.getPiece(startRow, startCol), startRow, startCol, destRow, destCol);
        board.moveChessOnBoard(startRow, startCol, destRow, destCol);
    }

    public boolean canMoveChess(Integer[] list){
        if (notInRange(list[0], list[1]) || notInRange(list[2], list[3])) {
            System.out.println("The move is out of range");
            return false;
        }
        if (!pathAvailable(list[0], list[1], list[2], list[3])){
            return false;
        }
        Piece movingPiece = board.getPiece(list[0], list[1]);
        Piece targetPiece = board.getPiece(list[2], list[3]);
        if (movingPiece == null){
            System.out.println("The moving piece is not choose");
            return false;
        }
        if (targetPiece != null && movingPiece.canKill(targetPiece)) {
           return true;
        }
        if (targetPiece == null && movingPiece.canMove(list[2], list[3])){
            return true;
        }
        System.out.println("This move is not valid");
        return false;
    }

    public boolean isMovingWhite(int startRow, int startCol){
        return board.getPiece(startRow, startCol).getColor() == ChessColor.white;
    }

    public boolean getCorrectCoordinate(Integer[] list, ChessColor color) {
        if (list == null || !canMoveChess(list)){
            return false;
        }
        if (list.length != 4){
            System.out.println("The coordinate input in not valid");
            return false;
        }

        if (color == ChessColor.white && !isMovingWhite(list[0], list[1])){
            System.out.println("You can only move WHITE chess");
            return false;
        }
        else if (color == ChessColor.black && isMovingWhite(list[0], list[1])){
            System.out.println("You can only move BLACK chess");
            return false;
        }
        return true;
    }

    public void playChess(){
        System.out.println("Game start!");
        Integer[] listWhite;
        Integer[] listBlack;
        while (!board.gameEnd()) {
            listWhite = null;
            listBlack = null;
            board.printBoard();
            System.out.println("\n White turn");
            while(!getCorrectCoordinate(listWhite, ChessColor.white)){
                listWhite = inputCoordinate();
            }
            moveChess(listWhite[0], listWhite[1], listWhite[2], listWhite[3]);
            if (board.gameEnd()){
                break;
            }

            board.printBoard();
            System.out.println("\n Black turn");
            while(!getCorrectCoordinate(listBlack, ChessColor.black)){
                listBlack = inputCoordinate();
            }
            moveChess(listBlack[0], listBlack[1], listBlack[2], listBlack[3]);
        }
        ChessColor color = board.winner();
        System.out.println("The winner is " + color);
    }

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

    public static void main(String[] arg){
        Game game = new Game();
        game.playChess();
    }
}
