import java.util.Random;

public class Player {
    // Shared random generator for player skill ratings.
    private static final Random random = new Random();

    // These values describe one player.
    private String name;
    private int skillRating;

    public Player(String name) {
        this.name = name;
        // nextInt(6) gives a random number from 0 to 5.
        skillRating = random.nextInt(6);
    }

    public String getName() {
        return name;
    }

    public int getSkillRating() {
        return skillRating;
    }
}
