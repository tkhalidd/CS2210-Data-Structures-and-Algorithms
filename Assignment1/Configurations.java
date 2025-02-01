// This program was written by Tazrin Khalid - student # 251294394. This class represents game board configurations and provides methods to initialize the game board, check for wins/draws and evaluating game state. 

import java.util.LinkedList;

public class Configurations {
    
    // Variables to store the board, its size, length to win, and max levels
    private char[][] board;
    private int boardSize;
    private int lengthToWin;
    private int maxLevels;
    
    // Constructor to initialize the board and its properties
    public Configurations(int boardSize, int lengthToWin, int maxLevels) {
        this.boardSize = boardSize;
        this.lengthToWin = lengthToWin;
        this.maxLevels = maxLevels;
        this.board = new char[boardSize][boardSize];
        boardInitialize();
    }
    
    // Method to initialize the board with empty spaces
    public void boardInitialize() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                this.board[row][col] = ' ';
            }
        }
    }
    
    // Method to create a hash dictionary for configurations
    public HashDictionary createDictionary() {
        int hashTableSize = 9931;
        return new HashDictionary(hashTableSize);
    } 
    
    // Method to check if the current configuration is repeated
    public int repeatedConfiguration(HashDictionary hashTable) {
        StringBuilder boardString = new StringBuilder();
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                boardString.append(board[row][col]);
            }
        }
        return hashTable.get(boardString.toString());
    }
    
    // Method to add a configuration to the dictionary
    public void addConfiguration(HashDictionary hashDictionary, int score) {
        StringBuilder boardString = new StringBuilder();
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                boardString.append(board[row][col]);
            }
        }
        
        // Check if configuration is not repeated, then add it to the dictionary
        if (repeatedConfiguration(hashDictionary) == -1) {
            hashDictionary.put(new Data(boardString.toString(), score));
        }
    }
    
    // Method to save a play on the board
    public void savePlay(int row, int col, char symbol) {
        board[row][col] = symbol;
    }
    
    // Method to check if a square on the board is empty
    public boolean squareIsEmpty(int row, int col) {
        return board[row][col] == ' ';
    }
    
    // Method to check if a player with a given symbol wins
    public boolean wins(char symbol) {
        return xShapeWin(symbol) || plusShapeWin(symbol);
    }
    
    // Method to check for a win in a diagonal shape
    private boolean xShapeWin(char symbol) {
        for (int row = 1; row < boardSize - 1; row++) {
            for (int col = 1; col < boardSize - 1; col++) {
                if (board[row][col] == symbol) {
                    int counter = 1;
                    // Check for consecutive symbols in the diagonal directions
                    for (int k = 1; k < lengthToWin; k++) { // k value as indicated in assignment instructions
                        if (row + k < boardSize && col - k >= 0 && board[row + k][col - k] == symbol) {
                            counter++;
                        }
                        if (row + k < boardSize && col + k < boardSize && board[row + k][col + k] == symbol) {
                            counter++;
                        }
                        if (row - k >= 0 && col - k >= 0 && board[row - k][col - k] == symbol) {
                            counter++;
                        }
                        if (row - k >= 0 && col + k < boardSize && board[row - k][col + k] == symbol) {
                            counter++;
                        }
                    }
                    // If enough consecutive symbols are found, return true
                    if (counter >= lengthToWin) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    // Method to check for a win in a plus shape
    private boolean plusShapeWin(char symbol) {
        for (int row = 1; row < boardSize - 1; row++) {
            for (int col = 1; col < boardSize - 1; col++) {
                if (board[row][col] == symbol) {
                    int counter = 1;
                    // Check for consecutive symbols in the plus directions
                    for (int k = 1; k < lengthToWin; k++) { // k value as indicated in assignment instructions
                        if (col + k < boardSize && board[row][col + k] == symbol) {
                            counter++;
                        }
                        if (col - k >= 0 && board[row][col - k] == symbol) {
                            counter++;
                        }
                        if (row + k < boardSize && board[row + k][col] == symbol) {
                            counter++;
                        }
                        if (row - k >= 0 && board[row - k][col] == symbol) {
                            counter++;
                        }
                    }
                    // If enough consecutive symbols are found, return true
                    if (counter >= lengthToWin) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    // Method to check if the game is a draw
    public boolean isDraw() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (this.board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    } 
    
    // Method to evaluate the current state of the board
    public int evalBoard() {
        if (wins('O')) {
            return 3; // 'O' wins
        } else if (wins('X')) {
            return 0; // 'X' wins
        } else if (isDraw()) {
            return 2; // It's a draw
        } else {
            return 1; // Game still in progress
        }
    }
}
