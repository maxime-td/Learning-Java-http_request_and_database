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
                System.out.println(languages.getString("exit_input", languageIndex));

            } else if (city.equals("english")) { // Change language to english
                languageIndex = 1;

            } else if (city.equals("français")) { // Change language to french
                languageIndex = 0;

            } else { // Valid input
                try {
                    // Checks if the answer is in the database
                    float reponseBase = Base.main(city, 444.4f);

                    if (reponseBase == -44.4f) { // If not in the database
                        response = 0;
                        URL url = new URL(URL_PART1 + city + URL_PART2); // Creates the full URL

                        // Gets the response from the website
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                        response = StreamReader.readStream(in, city); // Parse the response

                        float unused = Base.main(city, response); // Adds the response to the database

                        // Output
                        System.out.println(String.format(languages.getString("temperature_output", languageIndex), city, response));

                    } else { // If in the database
                        System.out.println(String.format(languages.getString("temperature_output", languageIndex), city, reponseBase));
                    }

                } catch (IOException e) {
                    System.out.println(languages.getString("error", languageIndex));
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
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