// Player interface for Tic Tac Toe
public interface IPlayer {
    // Get player name
    String getName();

    // Get player symbol (X or O)
    char getSymbol();

    // Get number of wins
    @SuppressWarnings("unused")
    int getWins();

    // Increment win count
    void addWin();

    // Display player statistics
    void displayStats();

    // Get player move (position 1-9)
    int getMove(Board board);
}
