import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Make a list and add four players to it.
        List<Player> players = new ArrayList<>();
        players.add(new Player("Alex"));
        players.add(new Player("Jamie"));
        players.add(new Player("Morgan"));
        players.add(new Player("Taylor"));

        // Put the players in a random order for the tournament draw.
        Collections.shuffle(players);

        // Show the shuffled draw order and each player's skill rating.
        System.out.println("Tournament draw order:");
        for (Player player : players) {
            System.out.println(player.name + " (skill: " + player.skillRating + ")");
        }
    }
}
