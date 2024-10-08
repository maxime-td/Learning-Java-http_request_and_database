import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamReader {

    // Affichage du stream dans la console
    public static void readStream(InputStream inputStream) throws IOException {

        // Initialisation
        StringBuilder resultStringBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        System.out.println(resultStringBuilder.toString());
    }
}