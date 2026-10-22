import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Ask how many players there are; a knockout needs at least 2.
        System.out.print("How many players? ");
        int playerCount = input.nextInt();
        while (playerCount < 2) {
            System.out.print("Please enter at least 2 players: ");
            playerCount = input.nextInt();
        }

        // Give every player a random name.
        List<Player> players = new ArrayList<>();
        for (String name : NameGenerator.generateNames(playerCount)) {
            players.add(new Player(name));
        }

        // Run the tournament; it shuffles the draw itself.
        Tournament tournament = new Tournament(players);
        Player champion = tournament.playTournament();

        // Show each match result.
        System.out.println("\nTournament matches:");
        for (Match match : tournament.getMatches()) {
            System.out.println(match.getPlayer1().getName() + " vs " + match.getPlayer2().getName()
                    + " - winner: " + match.getWinner().getName()
                    + " (" + match.getPlayer1Games() + " - " + match.getPlayer2Games() + ")");
        }

        System.out.println("Champion: " + champion.getName());
        input.close();
    }
}
