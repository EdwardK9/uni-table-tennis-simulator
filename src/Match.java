import java.util.Random;

public class Match {
    // Shared random generator for point outcomes.
    private static final Random random = new Random();

    private Player player1;
    private Player player2;
    private int player1Games;
    private int player2Games;
    private Player winner;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    // Play games until one player has won two, then store the winner.
    public void playMatch() {
        while (player1Games < 2 && player2Games < 2) {
            playGame();
        }

        if (player1Games > player2Games) {
            winner = player1;
        } else {
            winner = player2;
        }
    }

    // Randomly award points until the game is over, then count the game.
    private void playGame() {
        int player1Points = 0;
        int player2Points = 0;

        while (!isGameOver(player1Points, player2Points)) {
            if (random.nextBoolean()) {
                player1Points++;
            } else {
                player2Points++;
            }
        }

        if (player1Points > player2Points) {
            player1Games++;
        } else {
            player2Games++;
        }
    }

    // A game ends when someone has 11+ points and leads by at least 2.
    // Package-private so the tests can check it directly.
    static boolean isGameOver(int points1, int points2) {
        return Math.max(points1, points2) >= 11 && Math.abs(points1 - points2) >= 2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getPlayer1Games() {
        return player1Games;
    }

    public int getPlayer2Games() {
        return player2Games;
    }

    public Player getWinner() {
        return winner;
    }
}
