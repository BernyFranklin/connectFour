package com.connectfour;

import java.util.Scanner;

/**
 * Create connect 4, 7 x 6 grid
 *
 */
public class App 
{
    private static final int length = 7;
    private static final int width = 6;
    private static final String red = "Red";
    private static final String black = "Black";
    // Main program
    public static void main( String[] args )
    {
        // Create empty array for board
        char[][] board = new char[width][length];
        // Bool for gameOver, initialized to false
        boolean gameOver = false;
        // Set Red to start
        String currentPlayer = red;
        // Set initial inputChar
        char inputChar = ' ';
        // New game
        board = newGame(board);
        welcome();
        // Loop until winner
        while(!gameOver){
            // Print board
            printBoard(board);
            // Get player input
            inputChar = getInput(currentPlayer);
            // Is col full?
            boolean colFull = false;
            colFull = isFull(colFull, board, inputChar);
            // If full restart loop
            if(colFull){
                System.out.printf("\n!!!!That column is full, please try again!!!!\n");
                continue;
            }
            // Update board
            updateBoard(board, inputChar, currentPlayer);
            // Check if winner
            // Switch player
            currentPlayer = switchPlayer(currentPlayer);
        }
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
    // Welcome message
    private static void welcome(){
        System.out.printf("\nWelcome to Connect Four!");
        printBigLine();
        System.out.printf("\nPlayers will alternate until they connect four in");
        System.out.printf("\na row horizontally, vertically, or diagonally");
        printBigLine();

    }
    // Get input
    private static char getInput(String currentPlayer){
        // Create scanner object
        Scanner scan = new Scanner(System.in);
        // Blank input
        String stringInput = "";
        char charInput = ' ';
        // validity check
        boolean validMove = false;
        // Loop until valid move
        while(!validMove){
            // Prompt for input
            System.out.printf("\n%s player select column: ", currentPlayer);
            // Get user input
            stringInput = scan.nextLine();
            // If user fails to input ANYTHING
            if (stringInput == ""){
                stringInput = " ";
            }
            // Take first char and go toUpper
            stringInput = stringInput.toUpperCase();
            charInput = stringInput.charAt(0);
            // Check if legal
            validMove = isLegal(charInput);
            // Print error if applicable
            if(!validMove){
                System.out.printf("\nInvalid column selection, please try again");
            }
        }
        return charInput;

    }
    // Check if input is legal
    private static boolean isLegal(char charInput){
        // Applicable options
        char[] legalOptions = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // Loop through to see if valid input
        for (int i = 0; i < legalOptions.length; i++){
            if (charInput == legalOptions[i]){
                return true;
            }
        }
        return false;
    }
    // Switch players
    private static String switchPlayer(String currentPlayer){
        // Check what currentPlayer is then switch
        if(currentPlayer == red){
            currentPlayer = black;
        }
        else{
            currentPlayer = red;
        }
        return currentPlayer;
    }
    // Update board
    private static char[][] updateBoard(char[][] board, char inputChar, String currentPlayer){
        // Get index
        int colIndex = getIndex(inputChar);
        // Proceed to update board
        for(int i = width - 1; i >= 0; i--){
            if(board[i][colIndex] == 'O'){
                // Take first letter of player string
                char marker = currentPlayer.charAt(0);
                board[i][colIndex] = marker;
                break;
            }
        }
        return board;
    }
    // Get index colum
    private static int getIndex(char inputChar){
        int colIndex;
        switch(inputChar){
            case 'A':
                colIndex = 0;
                break;
            case 'B':
                colIndex = 1;
                break;
            case 'C':
                colIndex = 2;
                break;
            case 'D':
                colIndex = 3;
                break;
            case 'E':
                colIndex = 4;
                break;
            case 'F':
                colIndex = 5;
                break;
            case 'G':
                colIndex = 6;
                break;
            default:
                // SHOULD never execute
                colIndex = 7;
                break;
        }
        return colIndex;
    }
    // Is full?
    private static boolean isFull(boolean colFull, char[][] board, char inputChar){
        // Get the index for column
        int colIndex = getIndex(inputChar);
        // Loop backwards through index to find first O
        for(int i = width - 1; i >= 0; i--){
            if(board[i][colIndex] == 'O'){
                return false;
            }
        }
        return true;
    }
}
