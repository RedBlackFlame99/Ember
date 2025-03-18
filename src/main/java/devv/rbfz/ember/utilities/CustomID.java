package devv.rbfz.ember.utilities;

import java.util.List;
import java.util.Random;

public class CustomID {
    public static String generate() {
        String template = "0 0 0 0 0 0 0 0";
        while (template.contains("0")) {
            template = template.replaceFirst("0", getRandomLetter());
        }
        template = template.replace(" ", "");
        return template;
    }

    public static String getRandomLetter() {
        List<String> letters = List.of(
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        );
        String letter = letters.get(new Random().nextInt(0, letters.size() - 1));
        int capitalChance = new Random().nextInt(1, 100);
        if (capitalChance <= 50) {
            letter = letter.toLowerCase();
        }
        return letter;

    }
}
