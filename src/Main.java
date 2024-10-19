import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    private String ville = "";
    public String reponse = null;
    private Scanner scanner;

    private static final String URL_PART1 = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static final String URL_PART2 = "&appid=7ee0dd93945ae31985996d2cf2d1d95d&exclude=minutely,hourly,daily,alerts&units=metric";

    public Main(Scanner scanner) {
        this.scanner = scanner;
    }

    private void printMenu() {
        System.out.println("\nDe quelle ville voulez vous la météo ? Taper exit pour quitter.");
        System.out.print("Votre choix : ");
    }

    public void run() {
        try {
            printMenu();
            ville = scanner.nextLine();

            if (ville.isEmpty()) {
                reponse = "Veuillez entrer le nom d'une ville.";
            } else if (ville.equals("exit")) {
                reponse = "Au revoir !";
            } else {
                try {
                    URL url = new URL(URL_PART1 + ville + URL_PART2);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    reponse = StreamReader.readStream(in, ville);
                } catch (IOException e) {
                    reponse = "Erreur de lecture de la ville.";
                }
            }
            System.out.println(reponse);
        } catch (Exception e) {
            reponse = "Erreur : " + e.getMessage();
            System.out.println(reponse);
        }
    }

    public String getReponse() {
        return reponse;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main(scanner);
        while (!main.ville.equals("exit")) {
            main.run();
        }
        scanner.close();
    }
}