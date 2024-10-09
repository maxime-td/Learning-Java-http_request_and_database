import java.io.BufferedInputStream;
import java.io.IOException;
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
        System.out.println("\nDe quelle ville voulez vous la météo ? Taper exit pour quitter.\n");
        System.out.print("Votre choix : ");
    }


    public static void main(String[] argv) {

        // Initialisation de l'URL
        HttpURLConnection urlConnection = null;

        try {
            // Initialisation des parties de l'URL
            String urlPart1 = "https://api.openweathermap.org/data/2.5/weather?q=";
            String urlPart2 = "&appid=7ee0dd93945ae31985996d2cf2d1d95d&exclude=minutely,hourly,daily,alerts&units=metrics";

            String ville = "";

            // Boucle principale du programme
            while (!ville.equals("exit")) {

                // Affichage du menu
                printMenu();
                ville = scanner.nextLine();

                // Traitement du choix de l'utilisateur
                if (!ville.equals("exit")) {
                    // Création de l'URL complète
                    URL url = new URL(urlPart1 + ville + urlPart2);

                    try {
                        // Récupération des informations
                        urlConnection = (HttpURLConnection) url.openConnection();
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                        // Lecture du stream
                        StreamReader.readStream(in);
                    } catch (IOException e) {
                        System.out.println("Erreur de lecture du ville " + ville);
                    }
                }
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
