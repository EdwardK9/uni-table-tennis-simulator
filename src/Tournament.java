import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tournament {
    private List<Player> remainingPlayers;
    private List<Match> matches = new ArrayList<>();

    public Tournament(List<Player> players) {
        remainingPlayers = new ArrayList<>(players);
        // Shuffle so the draw is different each run.
        Collections.shuffle(remainingPlayers);
    }

    // Play rounds until one player is left, then return the champion.
    public Player playTournament() {
        while (remainingPlayers.size() > 1) {
            List<Player> nextRound = new ArrayList<>();

            // Pair players in order; each match winner advances.
            for (int i = 0; i + 1 < remainingPlayers.size(); i += 2) {
                Match match = new Match(remainingPlayers.get(i), remainingPlayers.get(i + 1));
                match.playMatch();
                matches.add(match);
                nextRound.add(match.getWinner());
            }

            // An odd player out gets a bye straight into the next round.
            if (remainingPlayers.size() % 2 == 1) {
                nextRound.add(remainingPlayers.get(remainingPlayers.size() - 1));
            }

            remainingPlayers = nextRound;
        }
        return remainingPlayers.get(0);
    }

    public List<Match> getMatches() {
        return matches;
    }
}
