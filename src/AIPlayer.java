import java.util.ArrayList;
import java.util.Random;

// Computer-controlled player with strategic AI
public class AIPlayer implements IPlayer {
    private String name;
    private char symbol;
    private int wins;
    private Random random;

    // Initialize AI player
    public AIPlayer(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.wins = 0;
        this.random = new Random();
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

    // Get AI move: win > block > random
    @Override
    public int getMove(Board board) {
        ArrayList<Integer> available = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (board.isValidMove(i)) {
                available.add(i);
            }
        }

        // Try to win
        for (int pos : available) {
            board.makeMove(pos, symbol);
            if (board.checkWin(symbol)) {
                board.undoMove(pos);
                return pos;
            }
            board.undoMove(pos);
        }

        // Block opponent
        char opponent = (symbol == 'X') ? 'O' : 'X';
        for (int pos : available) {
            board.makeMove(pos, opponent);
            if (board.checkWin(opponent)) {
                board.undoMove(pos);
                return pos;
            }
            board.undoMove(pos);
        }

     /*   // Take center or corner (ACTIVATE if you want AI to be smarter)
        if (available.contains(5)) return 5;
        int[] corners = {1, 3, 7, 9};
        for (int corner : corners) {
            if (available.contains(corner)) return corner;
        }
*/
        // Random move
        return available.get(random.nextInt(available.size()));
    }
}

