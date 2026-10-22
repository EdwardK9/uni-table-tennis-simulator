import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Plain Java checks for the plan's Testing section.
// Run it with: javac *.java && java TableTennisTest
public class TableTennisTest {
    public static void main(String[] args) {
        testGameEnd();
        testMatchEnd();
        testTournamentStructure();
        testByesAndElimination();
        System.out.println("All tests passed.");
    }

    // Game ends only at 11+ points with a lead of at least 2.
    static void testGameEnd() {
        check(Match.isGameOver(11, 9), "11-9 should end the game");
        check(!Match.isGameOver(11, 10), "11-10 should not end the game");
        check(!Match.isGameOver(10, 10), "10-10 should not end the game");
        check(Match.isGameOver(12, 10), "12-10 should end the game");
        check(!Match.isGameOver(11, 11), "11-11 should not end the game");
        check(Match.isGameOver(9, 11), "9-11 should end the game");
        check(!Match.isGameOver(0, 0), "0-0 should not end the game");
    }

    // Match ends when a player has won 2 games, and the winner has 2.
    static void testMatchEnd() {
        Player a = new Player("A");
        Player b = new Player("B");
        for (int i = 0; i < 200; i++) {
            Match match = new Match(a, b);
            match.playMatch();
            int winnerGames = match.getWinner() == a ? match.getPlayer1Games() : match.getPlayer2Games();
            int loserGames = match.getWinner() == a ? match.getPlayer2Games() : match.getPlayer1Games();
            check(winnerGames == 2, "winner should have exactly 2 games");
            check(loserGames < 2, "loser should have fewer than 2 games");
        }
    }

    // Each round halves the field, one champion remains, and matches = players - 1.
    static void testTournamentStructure() {
        for (int n = 2; n <= 20; n++) {
            Tournament tournament = new Tournament(makePlayers(n));
            Player champion = tournament.playTournament();
            List<List<Match>> rounds = tournament.getRounds();

            int totalMatches = 0;
            for (List<Match> round : rounds) {
                totalMatches += round.size();
            }
            check(totalMatches == n - 1, n + " players should give " + (n - 1) + " matches");

            List<Match> finalRound = rounds.get(rounds.size() - 1);
            check(finalRound.size() == 1, "last round should be a single match");
            Match finalMatch = finalRound.get(0);
            check(finalMatch.getWinner() == champion, "champion should win the final");
        }
    }

    // Losers never appear again; a player with a bye skips a round without losing.
    static void testByesAndElimination() {
        // 3 players: one bye in round 1, so round 1 has 1 match and round 2 has 1 match.
        Tournament three = new Tournament(makePlayers(3));
        three.playTournament();
        check(three.getRounds().size() == 2, "3 players should take 2 rounds");
        check(three.getRounds().get(0).size() == 1, "3 players: round 1 should have 1 match");

        for (int n = 2; n <= 20; n++) {
            Tournament tournament = new Tournament(makePlayers(n));
            tournament.playTournament();
            Set<Player> eliminated = new HashSet<>();
            for (List<Match> round : tournament.getRounds()) {
                for (Match match : round) {
                    check(!eliminated.contains(match.getPlayer1()), "eliminated player played again");
                    check(!eliminated.contains(match.getPlayer2()), "eliminated player played again");
                    Player loser = match.getWinner() == match.getPlayer1()
                            ? match.getPlayer2() : match.getPlayer1();
                    eliminated.add(loser);
                }
            }
        }
    }

    static List<Player> makePlayers(int count) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            players.add(new Player("P" + i));
        }
        return players;
    }

    static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("FAILED: " + message);
        }
    }
}
