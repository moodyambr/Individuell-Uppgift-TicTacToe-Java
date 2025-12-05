import java.util.Scanner;

// Manages Tic Tac Toe game logic and flow
public class Game {
    private Board board;
    private IPlayer playerOne;
    private IPlayer playerTwo;
    private IPlayer currentPlayer;
    private Scanner scanner;
    private boolean gameRunning;

    // Initialize game with two human players
    public Game(String nameOne, String nameTwo, Scanner scanner) {
        this.board = new Board();
        this.playerOne = new Player(nameOne, 'X', scanner);
        this.playerTwo = new Player(nameTwo, 'O', scanner);
        this.currentPlayer = playerOne;
        this.scanner = scanner;
        this.gameRunning = true;
    }

    // Initialize game with human and AI player
    public Game(String humanName, String aiName, boolean isHumanX, Scanner scanner) {
        this.board = new Board();

        if (isHumanX) {
            this.playerOne = new Player(humanName, 'X', scanner);
            this.playerTwo = new AIPlayer(aiName, 'O');
        } else {
            this.playerOne = new AIPlayer(aiName, 'X');
            this.playerTwo = new Player(humanName, 'O', scanner);
        }

        this.currentPlayer = playerOne;
        this.scanner = scanner;
        this.gameRunning = true;
    }

    // Main game loop for multiple rounds
    public void playGame() {
        System.out.println("=== WELCOME TO TIC TAC TOE ===\n");

        while (gameRunning) {
            playRound();
            if (gameRunning) {
                if (!playAgain()) {
                    gameRunning = false;
                }
            }
        }

        displayFinalStats();
    }

    // Play one round until win or draw
    private void playRound() {
        board.resetBoard();
        boolean roundOver = false;

        while (!roundOver) {
            board.displayBoard();
            System.out.println("It's " + currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");

            int position = currentPlayer.getMove(board);
            board.makeMove(position, currentPlayer.getSymbol());

            // Check if current player won
            if (board.checkWin(currentPlayer.getSymbol())) {
                board.displayBoard();
                System.out.println("🎉 " + currentPlayer.getName() + " wins! 🎉\n");
                currentPlayer.addWin();
                roundOver = true;
            }
            // Check if board is full (draw)
            else if (board.isBoardFull()) {
                board.displayBoard();
                System.out.println("It's a draw! No winner this round.\n");
                roundOver = true;
            }
            // Switch to other player
            else {
                switchPlayer();
            }
        }
    }

    // Switch to other player
    private void switchPlayer() {
        currentPlayer = (currentPlayer == playerOne) ? playerTwo : playerOne;
    }

    // Ask if player wants another round
    private boolean playAgain() {
        while (true) {
            System.out.print("Play another round? (yes/no): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes") || input.equals("y")) {
                System.out.println();
                return true;
            } else if (input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.out.println("Error: Please enter 'yes' or 'no'.\n");
            }
        }
    }

    // Display final game statistics
    private void displayFinalStats() {
        System.out.println("\n=== FINAL STATISTICS ===");
        playerOne.displayStats();
        playerTwo.displayStats();
        System.out.println("\nThank you for playing Tic Tac Toe!");
    }
}

