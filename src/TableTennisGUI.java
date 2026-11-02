import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

public class TableTennisGUI extends JFrame {
    // Spinner to pick the player count (at least 2), a box for the matches, a label for the champion.
    private final JSpinner playerCount = new JSpinner(new SpinnerNumberModel(8, 2, 1000, 1));
    private final JTextArea results = new JTextArea(20, 50);
    private final JLabel champion = new JLabel("Press Play to start a tournament.");

    public TableTennisGUI() {
        super("Table Tennis Simulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton play = new JButton("Play");
        play.addActionListener(e -> runTournament());

        JPanel top = new JPanel();
        top.add(new JLabel("Players:"));
        top.add(playerCount);
        top.add(play);

        results.setEditable(false);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(results), BorderLayout.CENTER);
        add(champion, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    // Make the players, play the tournament, then show each round and the champion.
    private void runTournament() {
        List<Player> players = new ArrayList<>();
        for (String name : NameGenerator.generateNames((Integer) playerCount.getValue())) {
            players.add(new Player(name));
        }

        Tournament tournament = new Tournament(players);
        Player winner = tournament.playTournament();

        results.setText("");
        List<List<Match>> rounds = tournament.getRounds();
        for (int round = 0; round < rounds.size(); round++) {
            results.append("Round " + (round + 1) + ":\n");
            for (Match match : rounds.get(round)) {
                results.append(match.getPlayer1().getName() + " vs " + match.getPlayer2().getName()
                        + " - winner: " + match.getWinner().getName()
                        + " (" + match.getPlayer1Games() + " - " + match.getPlayer2Games() + ")\n");
            }
            results.append("\n");
        }
        champion.setText("Champion: " + winner.getName());
    }
}
