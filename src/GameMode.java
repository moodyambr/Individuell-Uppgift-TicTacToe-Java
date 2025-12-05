import java.util.Scanner;

// Handles game mode selection (Human vs Human or Human vs AI)
public class GameMode {
    private Scanner scanner;

    // Initialize GameMode selector
    public GameMode(Scanner scanner) {
        this.scanner = scanner;
    }

    // Display mode menu and get user choice
    public boolean selectGameMode() {
        while (true) {
            System.out.println("=== SELECT GAME MODE ===");
            System.out.println("1. Human vs Human");
            System.out.println("2. Human vs AI (Computer)");
            System.out.print("Choose mode (1 or 2): ");

            String input = scanner.nextLine().trim();

            if (input.equals("1")) {
                System.out.println();
                return false;
            } else if (input.equals("2")) {
                System.out.println();
                return true;
            } else {
                System.out.println("Error: Please enter 1 or 2.\n");
            }
        }
    }
}

