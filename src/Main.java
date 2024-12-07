import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Main {

    // Initialisations
    private String city = "";
    private final Scanner scanner;

    private final Languages languages = new Languages();
    int languageIndex = 0;

    // Response management
    public String responseString = "vide";
    public float response = 44;

    private static final String URL_PART1 = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static final String URL_PART2 = "&appid=7ee0dd93945ae31985996d2cf2d1d95d&exclude=minutely,hourly,daily,alerts&units=metric";

    public Main(Scanner scanner) {
        this.scanner = scanner;
    }

    private void printMenu() {
        System.out.println(languages.getString("menu_1_city", languageIndex));
        System.out.println(languages.getString("menu_2_language", languageIndex));
        System.out.print(languages.getString("menu_3_choice", languageIndex));
    }

    public void run() {
        try {
            printMenu();
            city = scanner.nextLine();

            if (city.isEmpty()) { // Empty input
                System.out.println(languages.getString("empty_input", languageIndex));

            } else if (city.equals("exit")) { // Exit input
                responseString = "Au revoir !";
                System.out.println(responseString);

            } else if (city.equals("english")) { // Change language to english
                languageIndex = 1;

            } else if (city.equals("français")) { // Change language to french
                languageIndex = 0;

            } else { // Valid input
                try {
                    float reponseBase = Base.main(city, 444.4f);

                    if (reponseBase == -44.4f) {
                        response = 0;
                        URL url = new URL(URL_PART1 + city + URL_PART2);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        response = StreamReader.readStream(in, city);
                        float unused = Base.main(city, response);
                        responseString = String.format("Température à %s: %.1f°C", city, response);
                        System.out.println(responseString);

                    } else {
                        responseString = String.format("Température à %s: %.1f°C", city, reponseBase);
                        System.out.println(responseString);
                    }

                } catch (IOException e) {
                    responseString = "Nom de city invalide ou connexion internet indisponible.";
                    System.out.println(responseString);
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner

        Main main = new Main(scanner); // Main class

        while (!main.city.equals("exit")) {
            main.run();
        }
        scanner.close();
    }
}