import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class StreamReader {

    // Affichage du stream dans la console
    public static void readStream(InputStream inputStream) throws IOException {

        try (JsonReader jsonReader = Json.createReader(new InputStreamReader(inputStream))) {
            JsonObject jsonObject = jsonReader.readObject();
            JsonObject main = jsonObject.getJsonObject("main");
            System.out.println("Température à " + Main.ville + ": " + main.getJsonNumber("temp").doubleValue() + "°C");
        }
    }
}