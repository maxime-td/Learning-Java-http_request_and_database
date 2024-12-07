import java.io.File;
import java.sql.*;


public class Base {

    private static final Languages languages = new Languages();

    // Function to see if the database exists or not
    private static boolean databaseExists() {
        File dbFile = new File("base.db");
        return dbFile.exists();
    }


    // Function to add the temperature to the database
    private static void addTemperature(Statement stmt, Timestamp timestamp, String ville, float temperature, int languageIndex) throws SQLException {
        String sql = "INSERT INTO temperature (timestamp, ville, temperature) " +
                "VALUES (" + timestamp.getTime() + ", '" + ville + "', " + temperature + ")";
        stmt.execute(sql);
        System.out.println(languages.getString("new_temp_to_base", languageIndex));
    }


    // Fouction to read the temperature
    private static float readTemperature(Statement stmt, String inputVille, Timestamp timestamp) throws SQLException {
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
    public static float main(String ville, float temperat, int languageIndex) {
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
                System.out.println(languages.getString("base_created", languageIndex));

            } else {
                conn = DriverManager.getConnection(url);
                stmt = conn.createStatement();
                System.out.println(languages.getString("connection_to_db", languageIndex));
            }

            if (temperat == 444.4f) { // Reads temperature
                float temp = readTemperature(stmt, ville, timestamp);

                if (temp == 444.4f) { // No valor in the database
                    retour = -44.4f; // Instruction need of a request

                } else {
                    retour = temp;
                }

            } else { // Adds the temperature
                addTemperature(stmt, timestamp, ville, temperat, languageIndex);
            }

            // Closing
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
