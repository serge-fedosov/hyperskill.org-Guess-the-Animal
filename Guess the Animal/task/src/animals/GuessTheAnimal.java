package animals;

import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

public class GuessTheAnimal {
    static final Random random = new Random();

    String[] POSITIVE_RESPONSES = {"y", "yes", "yeah", "yep", "sure", "right", "affirmative", "correct", "indeed",
            "you bet", "exactly", "you said it"};

    String[] NEGATIVE_RESPONSES = {"n", "no", "no way", "nah", "nope", "negative", "I don't think so", "yeah no"};

    String[] CLARIFY = {"I'm not sure I caught you: was it yes or no?", "Funny, I still don't understand, is it yes or no?",
            "Oh, it's too complicated for me: just tell me yes or no.", "Could you please simply say yes or no?",
            "Oh, no, don't try to confuse me: say yes or no."};

    String[] GOODBYE_TEXT = {"Goodbye", "Bye", "See you later", "See ya!", "Catch you later", "Have a good day", "Bye bye!"};

    public void greeting() {
        int hour = LocalTime.now().getHour();
        if (hour >= 5 && hour < 12) {
            System.out.println("Good morning");
        } else if (hour >= 12 && hour < 18) {
            System.out.println("Good afternoon");
        } else if (hour >= 18 && hour < 24) {
            System.out.println("Good evening");
        } else if (hour >= 0 && hour < 4) {
            System.out.println("Hi, Night Owl");
        } else if (hour == 4) {
            System.out.println("Hi, Early Bird");
        }
    }

    public void goodbye() {
        System.out.println();
        System.out.println(GOODBYE_TEXT[random.nextInt(GOODBYE_TEXT.length)]);
    }

    public void guessTheAnimal() {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Enter an animal:");

        String animal = scanner.nextLine().toLowerCase();

        if (animal.startsWith("the ")) {
            animal = changeArticle(animal);
        } else if (!animal.startsWith("a ") && !animal.startsWith("an ")) {
            animal = addArticle(animal);
        }

        System.out.println("Is it " + animal + "?");

        while (true) {
            String answer = scanner.nextLine().trim().toLowerCase();
            if (answer.endsWith(".") || answer.endsWith("!")) {
                answer = answer.substring(0, answer.length() - 1);
            }

            if (arrayContainsString(POSITIVE_RESPONSES, answer)) {
                System.out.println("You answered: Yes");
                return;
            } else if (arrayContainsString(NEGATIVE_RESPONSES, answer)) {
                System.out.println("You answered: No");
                return;
            }

            System.out.println(CLARIFY[random.nextInt(CLARIFY.length)]);
        }
    }

    private boolean arrayContainsString(String[] array, String value) {
        for (var str: array) {
            if (str.equals(value)) {
                return true;
            }
        }

        return false;
    }

    private String changeArticle(String animal) {
        return addArticle(animal.substring(4));
    }

    private String addArticle(String animal) {
        char ch = animal.charAt(0);
        return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'y') ? "an " + animal : "a " + animal;
    }
}
