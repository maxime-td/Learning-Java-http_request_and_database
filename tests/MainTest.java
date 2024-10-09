import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testValidCity() {
        String input = "Paris\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Main.main(new String[]{});
        assertTrue(Main.validCityBoolean);
    }

    @Test
    public void testInvalidCity() {
        String input = "InvalidCity\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Main.main(new String[]{});
        assertTrue(Main.invalidCityBoolean);
    }

    @Test
    public void testValidAndInvalidCity() {
        String input = "Paris\nInvalidCity\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Main.main(new String[]{});
        assertTrue(Main.validCityBoolean);
        assertTrue(Main.invalidCityBoolean);
    }

    @Test
    public void testEmptyInput() {
        String input = "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Main.main(new String[]{});
        assertTrue(Main.invalidCityBoolean);
    }

    @Test
    public void testSpecialCharacters() {
        String input = "Pàrís\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Main.main(new String[]{});
        assertTrue(Main.invalidCityBoolean);
    }
}