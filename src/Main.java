import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Main {

    private String ville = "";
    private final Scanner scanner;
    public String reponseString = "vide";
    public float reponse = 44;

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
                reponseString = "Veuillez entrer le nom d'une ville.";
                System.out.println(reponseString);

            } else if (ville.equals("exit")) {
                reponseString = "Au revoir !";
                System.out.println(reponseString);

            } else {
                try {
                    float reponseBase = Base.main(ville, 444.4f);

                    if (reponseBase == -44.4f) {
                        reponse = 0;
                        URL url = new URL(URL_PART1 + ville + URL_PART2);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        reponse = StreamReader.readStream(in, ville);
                        float unused = Base.main(ville, reponse);
                        reponseString = String.format("Température à %s: %.1f°C", ville, reponse);
                        System.out.println(reponseString);

                    } else {
                        reponseString = String.format("Température à %s: %.1f°C", ville, reponseBase);
                        System.out.println(reponseString);
                    }

                } catch (IOException e) {
                    reponseString = "Nom de ville invalide ou connexion internet indisponible.";
                    System.out.println(reponseString);
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
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