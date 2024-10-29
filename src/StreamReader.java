import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class StreamReader {

    public static float readStream(InputStream inputStream, String ville) throws IOException {
        try (JsonReader jsonReader = Json.createReader(new InputStreamReader(inputStream))) {
            JsonObject jsonObject = jsonReader.readObject();
            JsonObject main = jsonObject.getJsonObject("main");

            double temperature = main.getJsonNumber("temp").doubleValue();
            return (float)temperature;
        }
    }
}