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
            // Update board
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
}
