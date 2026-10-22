import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class NameGenerator {
    private static final String[] FIRST_NAMES = {
        "Alex", "Jamie", "Morgan", "Taylor", "Casey", "Riley", "Jordan", "Sam",
        "Charlie", "Drew", "Quinn", "Avery", "Blake", "Cameron", "Dakota", "Emerson",
        "Finley", "Harper", "Hayden", "Jesse", "Kendall", "Logan", "Parker", "Reese", "Rowan"
    };

    private static final String[] LAST_NAMES = {
        "Smith", "Jones", "Brown", "Taylor", "Wilson", "Davies", "Evans", "Thomas",
        "Roberts", "Johnson", "Walker", "Wright", "Robinson", "Thompson", "White", "Hughes",
        "Edwards", "Green", "Hall", "Wood", "Harris", "Lewis", "Martin", "Clarke", "Scott"
    };

    // Names come from the first/last list; codes are used once real names run out.
    private static final int MAX_REAL_NAMES = 100;

    // Leaves out easily confused characters such as O/0 and I/1.
    private static final String CODE_CHARACTERS = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private static final int CODE_LENGTH = 6;

    private static final Random random = new Random();

    // Return the requested number of unique names.
    public static List<String> generateNames(int count) {
        // Make every first/last combination, shuffle it, and take the first few.
        List<String> fullNames = new ArrayList<>();
        for (String firstName : FIRST_NAMES) {
            for (String lastName : LAST_NAMES) {
                fullNames.add(firstName + " " + lastName);
            }
        }
        Collections.shuffle(fullNames, random);

        List<String> names = new ArrayList<>();
        for (int i = 0; i < count && i < MAX_REAL_NAMES; i++) {
            names.add(fullNames.get(i));
        }

        // Any players beyond the real names get a random code.
        Set<String> used = new HashSet<>(names);
        while (names.size() < count) {
            String code = randomCode();
            if (used.add(code)) {
                names.add(code);
            }
        }

        return names;
    }

    // Build a random code such as "K7QX2M".
    private static String randomCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(CODE_CHARACTERS.charAt(random.nextInt(CODE_CHARACTERS.length())));
        }
        return code.toString();
    }
}
