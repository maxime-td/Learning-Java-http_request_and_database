import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Scanner pour lire les entrées de l'utilisateur
    private static Scanner scanner = new Scanner(System.in);


    // Affiche le menu principal à l'écran.
    private static void printMenu() {
        System.out.println("\nDe quelle ville voulez vous la météo ?");
        System.out.print("Votre choix : ");
    }

    // Lit un stream


    public static void main(String[] argv) {

        HttpURLConnection urlConnection = null;

        try {
            // Initialisation
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Clermont-Ferrand&appid=7ee0dd93945ae31985996d2cf2d1d95d&exclude=minutely,hourly,daily,alerts&units=metrics");


            // Boucle principale du programme
            while (true) {

                printMenu();
                String ville = scanner.nextLine();

                // Traitement du choix de l'utilisateur
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                StreamReader.readStream(in);
                // Appel à l'API
                // https://api.openweathermap.org/data/2.5/weather?q=Clermont-Ferrand&appid=7ee0dd93945ae31985996d2cf2d1d95d&exclude=minutely,hourly,daily,alerts&units=metrics

            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
