import java.io.File;
import java.sql.*;


public class Base {

    // Fonction pour voir si une BD existe ou pas
    private static boolean databaseExists() {
        File dbFile = new File("base.db");
        return dbFile.exists();
    }


    // Fonction pour ajouter une ligne
    private static void ajouterTemperature(Statement stmt, Timestamp timestamp, String ville, float temperature) throws SQLException {
        String sql = "INSERT INTO temperature (timestamp, ville, temperature) " +
                "VALUES (" + timestamp.getTime() + ", '" + ville + "', " + temperature + ")";
        stmt.execute(sql);
        System.out.println("Température ajoutée à la BD.");
    }


    // Fonction pour lire les valeurs
    private static float lireTemperatures(Statement stmt, String inputVille, Timestamp timestamp) throws SQLException {
        float temp = 444.4f;
        ResultSet rs = stmt.executeQuery("SELECT * FROM temperature WHERE ville = '" + inputVille + "'");
        while (rs.next()) {
            if ((timestamp.getTime() - rs.getLong("timestamp")) < 4000000) { // 4k secondes ~ 1h 4000000 999999999
                temp = rs.getFloat("temperature");
            }
        }
        rs.close();
        return temp;
    }


    // Main = création/connexion BD
    public static float main(String ville, float temperat) {
        float retour = 0.0f;

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String url = "jdbc:sqlite:base.db";

        Connection conn;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");

            if (!databaseExists()) {

                conn = DriverManager.getConnection(url);
                stmt = conn.createStatement();

                String sql = "CREATE TABLE IF NOT EXISTS temperature (" +
                        "timestamp TIMESTAMP NOT NULL," +
                        "ville TEXT NOT NULL," +
                        "temperature FLOAT NOT NULL" + ");";
                stmt.execute(sql);
                System.out.println("La table a été créée.");

            } else {
                conn = DriverManager.getConnection(url);
                stmt = conn.createStatement();
                System.out.println("Connexion à la base de données existante.");
            }

            if (temperat == 444.4f) { // Lecture des températures
                float temp = lireTemperatures(stmt, ville, timestamp);

                if (temp == 444.4f) { // Pas de valeur dans la BD
                    retour = -44.4f; // Instruction besoin request

                } else {
                    retour = temp;
                }

            } else { // Ajout de la températures
                ajouterTemperature(stmt, timestamp, ville, temperat);
            }

            // Fermeture des ressources
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return retour;
    }
}
