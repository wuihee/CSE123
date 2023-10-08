import java.util.*;

// Wuihee 
// 10/06/2023
// CSE 123 
// C0: Abstract Strategy Game
// TA: Heon Jwa

// ConnectFour implements AbstractStrategyGame and is an implementation of the classic
// Connect Four in the console represented with Strings.
public class ConnectFour implements AbstractStrategyGame {

    // Fields
    private int ROWS = 6;
    private int COLUMNS = 7;
    private int WINNING_LENGTH = 4;
    private String[][] board;
    private int[] rowPositions;
    private int currentPlayer;
    private int winner;

    /**
     * Initialize ConnectFour objects.
     */
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
        System.out.println("Please run ConnectFour through Client.java");
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

            Connect Four is a two-player connection game in which the players take turns dropping
            their tokens into a six-row vertically suspended grid. The pieces fall straight down,
            occupying the next available space within the column. The object of the game is to
            connect four of one's own tokens next to each other vertically, horizontally, or
            diagonally before your opponent.
            
            Here is how the game works:
            - Player 1 selects a column to drop their token into the grid.
            - Player 2 then selects a column to drop their token.
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
        if (winner == -1 && !isBoardFilled()) {
            return false;
        }
        return true;
    }

    /**
     * Identifies the winner of the game.
     * 
     * @return The index of the winner, which is -1 if the game is still in progress.
     * However, if the board is filled and there's a tie, return 0.
     */
    public int getWinner() {
        if (isBoardFilled()) {
            return 0;
        }
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
     * Check if the board is completely filled.
     * 
     * @return true if the board is filled and false otherwise.
     */
    private boolean isBoardFilled() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
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
                if (checkHorizontal(i, j) ||
                    checkVertical(i, j) ||
                    checkDiagonal(i, j) ||
                    checkAntiDiagonal(i, j)) {
                    winner = currentPlayer;
                }
            }
        }
    }

    /**
     * Check for a horizontal win starting at index (r, c).
     * 
     * @param r The row to start searching.
     * @param c The column to start searching.
     * @return true if winning sequence found and false otherwise.
     */
    private boolean checkHorizontal(int r, int c) {
        if (c <= COLUMNS - WINNING_LENGTH) {
            for (int i = 1; i < WINNING_LENGTH; i++) {
                if (board[r][c + i] == null || board[r][c + i - 1] != board[r][c + i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Check for a vertical win starting at index (r, c).
     * 
     * @param r The row to start searching.
     * @param c The column to start searching.
     * @return true if winning sequence found and false otherwise.
     */
    private boolean checkVertical(int r, int c) {
        if (r <= ROWS - WINNING_LENGTH) {
            for (int i = 1; i < WINNING_LENGTH; i++) {
                if (board[r + i][c] == null || board[r + i - 1][c] != board[r + i][c]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Check for a diagonal win starting at index (r, c).
     * 
     * @param r The row to start searching.
     * @param c The column to start searching.
     * @return true if winning sequence found and false otherwise.
     */
    private boolean checkDiagonal(int r, int c) {
        if (r >= WINNING_LENGTH - 1 && c <= COLUMNS - WINNING_LENGTH) {
            for (int i = 1; i < WINNING_LENGTH; i++) {
                if (board[r - i][c + i] == null || board[r - i][c + i] != board[r - i + 1][c + i - 1]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Check for an anti-diagonal win starting at index (r, c).
     * 
     * @param r The row to start searching.
     * @param c The column to start searching.
     * @return true if winning sequence found and false otherwise.
     */
    private boolean checkAntiDiagonal(int r, int c) {
        if (r >= WINNING_LENGTH - 1 && c >= WINNING_LENGTH - 1) {
            for (int i = 1; i < WINNING_LENGTH; i++) {
                if (board[r - i][c - i] == null || board[r - i][c - i] != board[r - i + 1][c - i + 1]) {
                    return false;
                }
            }
            return true;
        }
        return false;
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