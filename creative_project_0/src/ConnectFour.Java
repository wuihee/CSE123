import java.util.*;

// Wuihee 
// 10/06/2023
// CSE 123 
// C0: Abstract Strategy Game
// TA: Heon Jwa

public class ConnectFour implements AbstractStrategyGame {

    // Fields
    private int ROWS = 6;
    private int COLUMNS = 7;
    private int WINNING_LENGTH = 4;
    private String[][] board;
    private int[] rowPositions;
    private int currentPlayer;
    private int winner;

    ConnectFour() {
        board = new String[ROWS][COLUMNS];
        rowPositions = new int[COLUMNS];
        for (int i = 0; i < COLUMNS; i++) {
            rowPositions[i] = ROWS - 1;
        }
        currentPlayer = 1;
        winner = -1;
    }

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        // System.out.println(game.instructions());
        // System.out.println(game.toString());
        String[] row = {"X", "X", "X", "X", "o"};
        System.out.println(game.isWinningArray(row));
    }

    /**
     * Provides instructions on how to play the game.
     * 
     * The instructions include:
     * - The interpretation of the game state, as provided by {@link #toString}.
     * - How to perform moves.
     * - The conditions that determine the end of the game and winning criteria.
     * 
     * @return A String containing instructions on how to play.
     */
    public String instructions() {
        String instructions = """                                                                                  
            #####                                           #######                      
            #     #  ####  #    # #    # ######  ####  ##### #        ####  #    # #####  
            #       #    # ##   # ##   # #      #    #   #   #       #    # #    # #    # 
            #       #    # # #  # # #  # #####  #        #   #####   #    # #    # #    # 
            #       #    # #  # # #  # # #      #        #   #       #    # #    # #####  
            #     # #    # #   ## #   ## #      #    #   #   #       #    # #    # #   #  
             #####   ####  #    # #    # ######  ####    #   #        ####   ####  #    # 
                                                                                          
            Welcome to Connect Four!

            Connect Four is a two-player connection game in which the players first choose a
            color and then take turns dropping colored discs from the top into a seven-column,
            six-row vertically suspended grid. The pieces fall straight down, occupying the
            next available space within the column. The object of the game is to connect four
            of one's own discs of the same color next to each other vertically, horizontally,
            or diagonally before your opponent.
            
            Here is how the game works:
            - Player 1 selects a column to drop their disc into the grid.
            - Player 2 then selects a column to drop their disc.
            - Players alternate turns until one player gets four of their discs in a line or
              the grid is filled.
            - If the grid is filled and no player has four discs in a line, the game is a draw.
            
            Instructions for making a move:
            1. The current state of the grid will be displayed. Each column will be numbered.
            2. To make a move, enter the number of the column where you want to drop your disc.
            3. Ensure to check the state of the grid before making a move to see the available
               columns.
        """;
        return instructions;
    }

    /**
     * Generates a String representation of the current game state.
     * 
     * The representation will include all player-relevant information including:
     * - The board state.
     * - The scores.
     * 
     * @return A String reflecting the current game state.
     */
    public String toString() {
        String gameState = "";

        for (int i = 0; i < ROWS; i++) {
            String[] row = board[i];
            String rowString = Integer.toString(ROWS - i) + " |";

            for (String token : row) {
                if (token != null) {
                    rowString += token + " ";
                } else {
                    rowString += "  ";
                }
            }
            gameState += rowString + "\n";
        }

        gameState += "   -------------\n";
        gameState += "   1 2 3 4 5 6 7\n";

        return gameState;
    }

    /**
     * Check if the game is over.
     * 
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        if (winner == -1) {
            return false;
        }
        return true;
    }

    /**
     * Identifies the winner of the game.
     * 
     * @return The index of the winner, or -1 if the game is still in progress.
     */
    public int getWinner() {
        return winner;
    }

    /**
     * Determines which player is next to make a move.
     * 
     * @return The index of the next player, or -1 if the game has ended.
     */
    public int getNextPlayer() {
        if (winner == -1) {
            return currentPlayer;
        }
        return -1;
    }

    /**
     * Executes a move based on the player's input.
     * 
     * @param input A Scanner object to read the player's move.
     */
    public void makeMove(Scanner input) {
        System.out.print("Choose which column to place your token: ");
        int col = input.nextInt() - 1;
        addToken(col);
        checkForWinner();
        changePlayers();
    }

    /**
     * Adds player's token to the specified column.
     * 
     * @param col The column which the token will be placed in.
     * @throws IllegalArgumentException If the column is filled to the top or if
     * row is out of bounds.
     */
    private void addToken(int col) {
        if (col < 0 || col >= COLUMNS) {
            throw new IllegalArgumentException("Row is out of bounds!\n");
        }

        int row = rowPositions[col];
        if (row == -1) {
            throw new IllegalArgumentException("This column is full!\n");
        }

        if (currentPlayer == 1) {
            board[row][col] = "X";
        } else {
            board[row][col] = "O";
        }
        rowPositions[col]--;
    }

    /**
     * Checks the board for any winning formations after a token is added.
     */
    private void checkForWinner() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                // Check horizontals.
                if (checkHorizontal(i, j) || checkVertical(i, j) || checkDiagonal(i, j) || checkAntiDiagonal(i, j)) {
                    winner = currentPlayer;
                }
            }
        }
    }

    private boolean checkHorizontal(int i, int j) {

    }

    private boolean checkVertical(int i, int j) {

    }

    private boolean checkDiagonal(int i, int j) {

    }

    private boolean checkAntiDiagonal(int i, int j) {

    }

    /**
     * Utility method for changing between player 1 and 2 after each move.
     */
    private void changePlayers() {
        if (currentPlayer == 1) {
            currentPlayer = 2;
        } else {
            currentPlayer = 1;
        }
    }
}