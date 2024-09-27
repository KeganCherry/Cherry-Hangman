import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {

    String word;
    char[] guess;
    ArrayList<Character> incorrectGuesses = new ArrayList<>();
    String pathToWordsFile = "src/assets/words.txt";

    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public HangmanGame() {
        System.out.println("Welcome to the Game!");

        System.out.print("Would you like to enter a word, \"enter\", or use a randomly selected one, \"generate\"? - ");
        do {
            switch (sc.nextLine()) {
                case "enter":
                    while (word == null) {
                        System.out.print("Please enter a word only containing letters, and is more than 3 letters. - ");
                        word = sc.nextLine();
                        if (!validateWord(word)) {
                            System.out.println("Invalid word!");
                            word = null;
                        }
                    }
                    break;
                case "generate":
                    word = getRandomWord();
                    break;
            }
        } while (word == null);

        System.out.flush();

        guess = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            guess[i] = '_';
        }

        while (incorrectGuesses.size() < 10 && !winCheck()) {
            System.out.println(hangman());
            System.out.println(String.valueOf(guess));
            System.out.println("Wrong Guesses: " + incorrectGuesses);
            System.out.println("What letter would you like to guess? - ");
            String guessInput = sc.nextLine();

            if (guessInput.length() != 1 || !Character.isLetter(guessInput.charAt(0))) {
                System.out.println("Invalid Guess!");
                continue;
            }

            char guessChar = guessInput.charAt(0);

            if (alreadyGuessed(guessChar)) {
                System.out.println("Letter already guessed!");
                continue;
            }

            if (word.indexOf(guessChar) >= 0) {
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guessChar) {
                        guess[i] = guessChar;
                    }
                }
            } else {
                incorrectGuesses.add(guessChar);
            }
        }

        if (winCheck()) {
            System.out.println("You won! The word was: " + word);
            int score = 0;
            try {
                score = (word.length() / incorrectGuesses.size()) * 100;
            } catch (ArithmeticException e) {
                score = word.length() * 100;
            }
            System.out.print("Score: " + score + "\nWhat's your name? - ");
            SaveHandling.addEntry(sc.nextLine(), score);
            SaveHandling.displayScores();
        } else {
            System.out.println("You lose!");
            System.out.println("The word was: " + word);
        }
    }

    private boolean alreadyGuessed(char guessChar) {
        return String.valueOf(guess).indexOf(guessChar) >= 0 || incorrectGuesses.contains(guessChar);
    }

    private boolean winCheck() {
        for (char c : guess) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    private boolean validateWord(String word) {
        return word.matches("[a-zA-Z]+") && word.length() > 2;
    }

    private String getRandomWord() {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToWordsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error opening words file.");
        }
        if (words.isEmpty()) {
            System.out.println("No words found.");
            return null;
        }
        return words.get(rand.nextInt(words.size()));
    }

    public String hangman() {
        switch (incorrectGuesses.size()) {
            case 0: return "    +---+\n    |   |\n        |\n        |\n        |\n        |\n=========";
            case 1: return "    +---+\n    |   |\n    O   |\n        |\n        |\n        |\n=========";
            case 2: return "    +---+\n    |   |\n    O   |\n    |   |\n        |\n        |\n=========";
            case 3: return "    +---+\n    |   |\n    O   |\n   /|   |\n        |\n        |\n=========";
            case 4: return "    +---+\n    |   |\n    O   |\n   /|\\  |\n        |\n        |\n=========";
            case 5: return "    +---+\n    |   |\n    O   |\n   /|\\  |\n   /    |\n        |\n=========";
            case 6: return "    +---+\n    |   |\n    O   |\n   /|\\  |\n   / \\  |\n        |\n=========";
            case 7: return "    +---+\n    |   |\n    O   |\n  _/|\\  |\n   / \\  |\n        |\n=========";
            case 8: return "    +---+\n    |   |\n    O   |\n  _/|\\_ |\n   / \\  |\n        |\n=========";
            case 9: return "    +---+\n    |   |\n    O   |\n  _/|\\_ |\n  _/ \\  |\n        |\n=========";
            case 10: return "    +---+\n    |   |\n    O   |\n  _/|\\_ |\n  _/ \\_ |\n        |\n=========";
            default: return "    +---+\n    |   |\n   x x  |\n  _/|\\_ |\n  _/ \\_ |\n        |\n=========";
        }
    }
}
