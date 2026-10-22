import java.util.Random;

public class Player {
    // These values describe one player.
    String name;
    int skillRating;

    public Player(String name) {
        this.name = name;
        // nextInt(6) gives a random number from 0 to 5.
        Random random = new Random();
        skillRating = random.nextInt(6);
    }
}
