package animals;

import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

public class GuessTheAnimal {
    static final Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    String[] POSITIVE_RESPONSES = {"y", "yes", "yeah", "yep", "sure", "right", "affirmative", "correct", "indeed",
            "you bet", "exactly", "you said it"};

    String[] NEGATIVE_RESPONSES = {"n", "no", "no way", "nah", "nope", "negative", "I don't think so", "yeah no"};

    String[] CLARIFY = {"I'm not sure I caught you: was it yes or no?", "Funny, I still don't understand, is it yes or no?",
            "Oh, it's too complicated for me: just tell me yes or no.", "Could you please simply say yes or no?",
            "Oh, no, don't try to confuse me: say yes or no."};

    String[] GOODBYE_TEXT = {"Goodbye", "Bye", "See you later", "See ya!", "Catch you later", "Have a good day", "Bye bye!"};

    private String animal1;
    private String animal2;
    private String fact;
    private String fact2True;
    private String fact2False;
    private String fact3;
    private boolean animal2fact;

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

    private String removeArticle(String animal) {
        if (animal.startsWith("the ")) {
            return animal.substring(4);
        } else if (animal.startsWith("a ")) {
            return animal.substring(2);
        } else if (animal.startsWith("an ")) {
            return animal.substring(3);
        } else {
            return animal;
        }
    }

    private void setFact(String factString) {
        if (factString.endsWith(".") || factString.endsWith("?")) {
            factString = factString.substring(0, factString.length() - 1);
        }

        if (factString.startsWith("it can ")) {
            fact = factString.substring(7);
            fact2True = "can";
            fact2False = "can't";
            fact3 = "Can it";
        } else if (factString.startsWith("it has ")) {
            fact = factString.substring(7);
            fact2True = "has";
            fact2False = "doesn't have";
            fact3 = "Does it have";
        } else if (factString.startsWith("it is ")) {
            fact = factString.substring(6);
            fact2True = "is";
            fact2False = "isn't";
            fact3 = "Is it";
        } else {
            fact = null;
            fact2True = null;
            fact2False = null;
            fact3 = null;
        }
    }

    public void guessTheAnimal() {
        System.out.println();

        System.out.println("Enter the first animal:");
        animal1 = removeArticle(scanner.nextLine().trim().toLowerCase());
//        if (animal.startsWith("the ")) {
//            animal = changeArticle(animal);
//        } else if (!animal.startsWith("a ") && !animal.startsWith("an ")) {
//            animal = addArticle(animal);
//        }

        System.out.println("Enter the second animal:");
        animal2 = removeArticle(scanner.nextLine().trim().toLowerCase());

        do {
            System.out.printf("Specify a fact that distinguishes %s from %s.\n" +
                    "The sentence should be of the format: 'It can/has/is ...'.\n\n", addArticle(animal1), addArticle(animal2));

            setFact(scanner.nextLine().trim().toLowerCase());

            if (fact == null) {
                System.out.println("The examples of a statement:\n" +
                        " - It can fly\n" +
                        " - It has horn\n" +
                        " - It is a mammal");
            }
        } while (fact == null);

        System.out.printf("Is it correct for %s?\n", addArticle(animal2));

        while (true) {
            String answer = scanner.nextLine().trim().toLowerCase();
            if (answer.endsWith(".") || answer.endsWith("!")) {
                answer = answer.substring(0, answer.length() - 1);
            }

            if (arrayContainsString(POSITIVE_RESPONSES, answer)) {
                animal2fact = true;
                break;
            } else if (arrayContainsString(NEGATIVE_RESPONSES, answer)) {
                animal2fact = false;
                break;
            }

            System.out.println(CLARIFY[random.nextInt(CLARIFY.length)]);
        }

        System.out.println("I learned the following facts about animals:");
        System.out.printf("- The %s %s %s.\n", animal1, animal2fact ? fact2False : fact2True, fact);
        System.out.printf("- The %s %s %s.\n", animal2, animal2fact ? fact2True : fact2False, fact);

        System.out.println("I can distinguish these animals by asking the question:");

        System.out.printf("- %s %s?\n", fact3, fact);
    }
}
