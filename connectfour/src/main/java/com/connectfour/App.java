package com.connectfour;

/**
 * Create connect 4, 7 x 6 grid
 *
 */
public class App 
{
    private static final int length = 7;
    private static final int width = 6;
    public static void main( String[] args )
    {
        // Create empty array for board
        char[][] board = new char[width][length];
        // New game
        board = newGame(board);
        //print board
        printBoard(board);
    }
    // Empty game and restart
    private static char[][] newGame(char[][] board){
        // Loop through values and and fill with O's
        for(int i = 0; i < width; i++){
            for(int j = 0; j < length; j++){
                board[i][j] = 'O';
            }
        }
        return board;
    }
    // Print board
    private static void printBoard(char[][] board){
        // State board is current
        System.out.printf("\n\t\tCurrent Board");
        printBigLine();
        // Column headers
        System.out.printf("\nA\tB\tC\tD\tE\tF\tG");
        // Line
        printBigLine();
        //print board
        for(int i = 0; i < width; i++){
            System.out.printf("\n");
            for(int j = 0; j < length; j++){
                System.out.printf("%s\t", board[i][j]);
            }
        }
        printBigLine();
    }
    // Big line
    private static void printBigLine(){
        System.out.printf("\n--------------------------------------------------");
    }
}
