public class Board {
    private final char[] cells = new char[9];
    private static final char EMPTY = ' ';

    //Constructor initializes a new board with all cells empty.
    public Board() {
        resetBoard();
    }

    //Resets the board to all empty cells.

    public void resetBoard() {
        for (int i = 0; i < cells.length; i++) {
            cells[i] = EMPTY;
        }
    }

    //Displays the current board state in a formatted grid with proper styling.
    public void displayBoard() {
        System.out.println();
        System.out.println("    === TIC TAC TOE GAME ===");
        System.out.println("    +-------+-------+-------+");
        for (int row = 0; row < 3; row++) {
            int base = row * 3;
            System.out.printf("    |   %s   |   %s   |   %s   |%n",
                    displayCellContent(cells[base], base),
                    displayCellContent(cells[base + 1], base + 1),
                    displayCellContent(cells[base + 2], base + 2));
            System.out.println("    +-------+-------+-------+");
        }
        System.out.println();
    }

    //Displays cell content: either the symbol (X/O) or the position number if empty.

    private String displayCellContent(char cell, int index) {
        if (cell == EMPTY) {
            return String.valueOf(index + 1);
        } else {
            return String.valueOf(cell);
        }
    }

    //Attempts to place the symbol at a position (1-9).
    public boolean makeMove(int position, char symbol) {
        if (!isValidMove(position)) {
            return false;
        }
        cells[position - 1] = symbol;// Adjust for 0-based index for array access
        return true;
    }

    //Undoes a move by clearing the cell at the given position.
    //Used by AI for testing potential moves without modifying the actual board state.
    public void undoMove(int position) {
        if (position >= 1 && position <= 9) {
            cells[position - 1] = EMPTY;
        }
    }

    //Checks if a position is valid and empty.
    public boolean isValidMove(int position) {
        if (position < 1 || position > 9) {
            return false;
        }
        return cells[position - 1] == EMPTY;
    }

    //Checks if the board is completely full.
    public boolean isBoardFull() {
        for (char c : cells) {
            if (c == EMPTY) {
                return false;
            }
        }
        return true;
    }

    //Checks if a player has won by checking all winning combinations.

    public boolean checkWin(char symbol) {
        // Define all possible winning combinations (rows, columns, diagonals)
        int[][] winningCombinations = {
                //Rows
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                //Columns
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                //Diagonals
                {0, 4, 8},
                {2, 4, 6}
        };

        // Check each winning combination
        for (int[] combination : winningCombinations) {
            if (cells[combination[0]] == symbol &&
                    cells[combination[1]] == symbol &&
                    cells[combination[2]] == symbol) {
                return true;
            }
        }

        return false;
    }
}

