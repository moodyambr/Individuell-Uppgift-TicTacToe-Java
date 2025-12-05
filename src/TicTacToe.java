import java.util.Scanner;

// Main entry point for Tic Tac Toe game
public class TicTacToe {
    private TicTacToe() {
        // Utility class - prevent instantiation
    }

    // Entry point for the application
    @SuppressWarnings("unused")
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== TIC TAC TOE GAME ===\n");

        // Select game mode
        GameMode gameMode = new GameMode(scanner);
        boolean playAgainstAI = gameMode.selectGameMode();

        Game game;

        if (playAgainstAI) {
            // Human vs AI mode
            String humanName = getPlayerName(scanner, 1);

            // Ask who plays first
            boolean humanIsX = getHumanSymbol(scanner);

            game = new Game(humanName, "AI Opponent", humanIsX, scanner);
        } else {
            // Human vs Human mode
            String player1Name = getPlayerName(scanner, 1);
            String player2Name = getPlayerName(scanner, 2);

            game = new Game(player1Name, player2Name, scanner);
        }

        // Start the game
        game.playGame();

        scanner.close();
    }

    // Gets human player's symbol choice (X or O)
    private static boolean getHumanSymbol(Scanner scanner) {
        while (true) {
            System.out.print("Do you want to play as X (first) or O (second)? (X/O): ");
            String choice = scanner.nextLine().trim().toUpperCase();

            if (choice.equals("X")) {
                return true;
            } else if (choice.equals("O")) {
                return false;
            } else {
                System.out.println("Error: Please enter X or O.\n");
            }
        }
    }

    // Gets validated player name
    private static String getPlayerName(Scanner scanner, int playerNumber) {
        String name = "";
        boolean validName = false;

        while (!validName) {
            System.out.print("Enter Player " + playerNumber + " name: ");
            name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Error: Name cannot be empty. Please try again.\n");
            } else {
                validName = true;
            }
        }

        return name;
    }
}

