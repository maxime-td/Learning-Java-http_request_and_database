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

    public static void main(String[] argv) {

        try {
            // Initialisation


            // Boucle principale du programme
            while (true) {

                printMenu();
                String ville = scanner.nextLine();
                scanner.nextLine(); // Consomme le retour à la ligne restant

                // Traitement du choix de l'utilisateur
                // Appel à l'API

            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
