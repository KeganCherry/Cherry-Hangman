import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class SaveHandling {

    private static final String FILE_NAME = "src/assets/save.txt";

    public SaveHandling() {
        try {
            if (!Files.exists(Paths.get(FILE_NAME))) {
                Files.createFile(Paths.get(FILE_NAME));
            }
        } catch (IOException e) {
            System.out.println("Error reading leaderboard save file.");
        }
    }

    public static void displayScores() {
        List<ScoreEntry> scores = readScores();
        scores.sort((s1, s2) -> Integer.compare(s2.score, s1.score));
        System.out.printf("%-15s %-10s %-20s%n", "Name", "Score", "Date");
        System.out.println("------------------------------------------");
        for (ScoreEntry entry : scores) {
            System.out.printf("%-15s %-10d %-20s%n", entry.name, entry.score, entry.date);
        }
    }

    public static void addEntry(String name, int score) {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(name + "," + score + "," + date);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error reading leaderboard save file.");
        }
    }

    public static void flushScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("");
        } catch (IOException e) {
            System.out.println("Error reading leaderboard save file.");
        }
    }

    private static List<ScoreEntry> readScores() {
        List<ScoreEntry> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    scores.add(new ScoreEntry(parts[0], Integer.parseInt(parts[1]), parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading leaderboard save file.");
        }
        return scores;
    }

    private static class ScoreEntry {
        String name;
        int score;
        String date;

        ScoreEntry(String name, int score, String date) {
            this.name = name;
            this.score = score;
            this.date = date;
        }
    }
}
