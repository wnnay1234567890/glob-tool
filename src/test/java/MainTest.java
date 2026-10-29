import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private static final String NL = System.lineSeparator();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream out;

    @BeforeEach void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach void restore() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private String run(String input, String... args) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.main(args);
        return out.toString();
    }

    @Test void singleLine()    { assertEquals("1" + NL, run("Cat\n", "?at")); }
    @Test void multipleLines() { assertEquals("1" + NL + "1" + NL + "0" + NL, run("cat\nbat\npattern\n", "?at")); }
    @Test void noInput()       { assertEquals("", run("", "?at")); }
    @Test void noArgs()        { assertEquals("Usage: glob [PATTERN]" + NL, run("")); }
    @Test void tooManyArgs()   { assertEquals("Usage: glob [PATTERN]" + NL, run("", "a", "b")); }
}