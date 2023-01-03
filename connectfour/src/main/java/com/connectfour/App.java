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
    private static final String red = "red";
    private static final String black = "black";
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
        // Print board
        printBoard(board);
        // Loop until winner
        while(!gameOver){
            // Switch player
            currentPlayer = switchPlayer(currentPlayer);
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
            // Print board
            printBoard(board);
            // Check if winner
            gameOver = checkWinner(board, currentPlayer);
        }
        // Game over message
        System.out.printf("\n%s player wins!", currentPlayer);
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
    // Check for winner
    private static boolean checkWinner(char[][] board, String currentPlayer){
        boolean horizontal = false;
        boolean vertical = false;
        boolean diagonal = false;
        char marker = currentPlayer.charAt(0);
        // Check horizontal
        horizontal = checkHoriz(board, marker);
        // Check vertical
        vertical = checkVert(board, marker);
        // Check diagonal
        diagonal = checkDiag(board, marker);

        if(horizontal || vertical || diagonal){
            return true;
        }
        return false;
    }
    // Check horizontal
    private static boolean checkHoriz(char[][] board, char marker){
        int count = 0;
        for(int i = 0; i < width; i++){
            // Restart count every row
            count = 0;
            for(int j = 0; j < length; j++){
                // If empty restart count
                if(board[i][j] != marker){
                    count = 0;
                }
                // Update counter if players marker
                else if(board[i][j] == marker){
                    count++;
                }
                // If 4 in a row return true
                if(count == 4){
                    return true;
                }
            }
        }
        return false;
    }
    // Check vertical
    private static boolean checkVert(char[][] board, char marker){
        int count = 0;
        for(int i = 0; i < length; i++){
            count = 0;
            for(int j = 0; j < width; j++){
                // If empty restart count
                if(board[j][i] != marker){
                    count = 0;
                }
                else if (board[j][i] == marker){
                    count++;
                }
                // If 4 in a row reached return true
                if(count == 4){
                    return true;
                }
            }
        }
        // If reached, not 4 in row
        return false;
    }
    // Check diagonal
    private static boolean checkDiag (char[][] board, char marker){
        boolean winnerFound = false;
        // iterate through entire board
        for(int i = 0; i < width; i++){
            for(int j = 0; j < length; j++){
                // Check forward diagonals
                winnerFound = forCheck(board, i, j, marker);
                if (winnerFound){
                    break;
                }
            }
            if(winnerFound){
                break;
            }
        }
        return winnerFound;
    }
    // Forward diagonal 
    private static boolean forCheck(char[][] board, int row, int col, char marker){
        int count = 0;
        // If col < 3 or row > 2 no forward diag
        if(row > 2 || col < 3){
            return false;
        }
        else{
            // Iterate across the board
            for(int i = row, j = col; i < width && j >= 0; i++, j--){
                // If matches marker, count++
                if(board[row][col] == marker){
                    count++;
                }
                else{
                    // Reinitiate count
                    count = 0;
                }
            }
        }
        // If 4 in a row return true
        if(count == 4){
            return true;
        }
        // No winner
        return false;
    }
}
