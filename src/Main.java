import java.util.Scanner;

public class Main {

    static HangmanGame currentGame;
    static Scanner sc = new Scanner(System.in);
    static SaveHandling saveHandling = new SaveHandling();

    public static void main(String[] args) {
        System.out.println("\n" + "\u001B[30m" +
                " ██████╗██╗  ██╗███████╗██████╗ ██████╗ ██╗   ██╗     ██╗  ██╗ █████╗ ███╗   ██╗ ██████╗ ███╗   ███╗ █████╗ ███╗   ██╗\n" +
                "██╔════╝██║  ██║██╔════╝██╔══██╗██╔══██╗╚██╗ ██╔╝     ██║  ██║██╔══██╗████╗  ██║██╔════╝ ████╗ ████║██╔══██╗████╗  ██║\n" +
                "██║     ███████║█████╗  ██████╔╝██████╔╝ ╚████╔╝█████╗███████║███████║██╔██╗ ██║██║  ███╗██╔████╔██║███████║██╔██╗ ██║\n" +
                "██║     ██╔══██║██╔══╝  ██╔══██╗██╔══██╗  ╚██╔╝ ╚════╝██╔══██║██╔══██║██║╚██╗██║██║   ██║██║╚██╔╝██║██╔══██║██║╚██╗██║\n" +
                "╚██████╗██║  ██║███████╗██║  ██║██║  ██║   ██║        ██║  ██║██║  ██║██║ ╚████║╚██████╔╝██║ ╚═╝ ██║██║  ██║██║ ╚████║\n" +
                " ╚═════╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝        ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝ ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝\n" +
                "By Kegan Cherry                                                                                                      \n");

        while (true) { // interp. response
            System.out.print("Would you like to view the leaderboard, \"v\", start a game, \"n\", or exit program \"exit\"? - ");
            switch(sc.nextLine()) {
                case "v":
                    SaveHandling.displayScores();
                    break;
                case "n":
                    currentGame = new HangmanGame();
                    break;
                case "exit":
                    System.out.println("Thanks for playing!");
                    System.exit(0);
            }
        }

    }

}
