import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreationBaseMain {
    public static void main(String[] args) {

        try {
            Class.forName("org.sqlite.JDBC");

            // Création de la base
            String url = "jdbc:sqlite:base.db";

            Connection conn = DriverManager.getConnection(url);

            if (conn != null) {
                System.out.println("Base créée.");
            }

            /*
            // Création de la table
            String sql = "CREATE TABLE IF NOT EXISTS temperature (" +
                    "timestamp TIMESTAMP NOT NULL," +
                    "ville TEXT NOT NULL," +
                    "temperature FLOAT NOT NULL" +
                    ");";

            Statement stmt = conn.createStatement();
                stmt.execute(sql);
                System.out.println("La table a été créée.");
             */

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
