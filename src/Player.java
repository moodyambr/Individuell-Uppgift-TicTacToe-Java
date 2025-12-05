// Human player in Tic Tac Toe game
public class Player implements IPlayer {
    private String name;
    private char symbol;
    private int wins;
    protected java.util.Scanner scanner;
    // Initialize human player
    public Player(String name, char symbol, java.util.Scanner scanner) {
        this.name = name;
        this.symbol = symbol;
        this.wins = 0;
        this.scanner = scanner;
    }
    // Get player name
    @Override
    public String getName() {
        return name;
    }
    // Get player symbol (X or O)
    @Override
    public char getSymbol() {
        return symbol;
    }
    // Get number of wins
    @Override
    public int getWins() {
        return wins;
    }
    // Increment win count
    @Override
    public void addWin() {
        this.wins++;
    }
    // Display player statistics
    @Override
    public void displayStats() {
        System.out.println(name + " (" + symbol + ") - Wins: " + wins);
    }
    // Get validated player move (1-9)
    @Override
    public int getMove(Board board) {
        int position = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter position (1-9): ");
                String input = scanner.nextLine().trim();
                // Check for empty input
                if (input.isEmpty()) {
                    System.out.println("Error: Please enter a number.\n");
                    continue;
                }
                // Try to parse the input
                position = Integer.parseInt(input);
                // Validate the move
                if (!board.isValidMove(position)) {
                    System.out.println("Error: Invalid position or cell already taken. Try again.\n");
                    continue;
                }
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number between 1 and 9.\n");
            }
        }
        return position;
    }
}
