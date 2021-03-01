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

    public boolean inRange(int row, int col){
        return row < 0 || col < 0 || row > 7 || col > 7;
    }

    public void moveChess(int startRow, int startCol, int destRow, int destCol){
        Piece movingPiece = board.getPiece(startRow, startCol);
        Piece targetPiece = board.getPiece(destRow, destCol);
        if (movingPiece.canKill(targetPiece) || movingPiece.canMove(destRow, destCol)) {
            board.moveChessOnBoard(startRow, startCol, destRow, destCol);
        }
    }

    public boolean canMoveChess(Integer[] list){
        if (list == null){
            return false;
        }

        if (list.length != 4){
            System.out.println("The coordinate input in not valid");
            return false;
        }

        if (!inRange(list[0], list[1]) || !inRange(list[2], list[3])) {
            System.out.println("The move is out of range");
            return false;
        }
        if (!pathAvailable(list[0], list[1], list[2], list[3])){
            System.out.println("The move is not valid!");
            return false;
        }
        return true;
    }

    public boolean isMovingWhite(int startRow, int startCol){
        return board.getPiece(startRow, startCol).getColor() == ChessColor.white;
    }

    public Integer[] getCorrectCoordinate(Integer[] list, ChessColor color) {
        while (!canMoveChess(list)) {
            list = inputCoordinate();
        }
        if (color == ChessColor.white){
            while (!isMovingWhite(list[0], list[1])){
                System.out.println("You can move the WHITE chess only!");
                list = inputCoordinate();
            }
        }
        else if (color == ChessColor.black){
            while(isMovingWhite(list[0], list[1])){
                System.out.println("You can move the BLACK chess only!");
                list = inputCoordinate();
            }
        }
        return list;
    }

    public void playChess(){
        System.out.println("Game start!");
        Integer[] listWhite = null;
        Integer[] listBlack = null;
        while (!board.gameEnd()) {
            board.printBoard();
            System.out.println("\n White turn");
            listWhite = getCorrectCoordinate(listWhite, ChessColor.white);
            moveChess(listWhite[0], listWhite[1], listWhite[2], listWhite[3]);

            board.printBoard();
            System.out.println("\n Black turn");
            listBlack = getCorrectCoordinate(listBlack, ChessColor.black);
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
