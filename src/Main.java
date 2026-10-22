import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Make a list and add four players to it.
        List<Player> players = new ArrayList<>();
        players.add(new Player("Alex"));
        players.add(new Player("Jamie"));
        players.add(new Player("Morgan"));
        players.add(new Player("Taylor"));

        // Run the tournament; it shuffles the draw itself.
        Tournament tournament = new Tournament(players);
        Player champion = tournament.playTournament();

        // Show each match result.
        System.out.println("Tournament matches:");
        for (Match match : tournament.getMatches()) {
            System.out.println(match.getPlayer1().getName() + " vs " + match.getPlayer2().getName()
                    + " - winner: " + match.getWinner().getName()
                    + " (" + match.getPlayer1Games() + " - " + match.getPlayer2Games() + ")");
        }

        System.out.println("Champion: " + champion.getName());
    }
}
