package cdv.ls.log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link ConsoleLog}
 *
 * @author Dmitry Coolga
 *         10.08.2016 06:32
 */
public class ConsoleLogTest {

    private PrintStream standardOutput;

    private ByteArrayOutputStream outputMock;

    private Log log = new ConsoleLog();

    @Before
    public void mockOutput() {
        standardOutput = System.out;
        outputMock = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputMock));
    }

    @After
    public void restoreOutput() {
        System.setOut(standardOutput);
    }

    @Test
    public void testPrintMessageWithoutArguments() {
        String message = "message";
        log.print(message);
        assertConsoleOutput(message);
    }

    @Test
    public void testPrintMessageWithArguments() {
        log.print("{0} {1} {2}", 1, 2, 3);
        assertConsoleOutput("1 2 3");
    }

    @Test
    public void testPrintMessageWithNullArgument() {
        log.print("{0} {1} {2}", 1, null, 3);
        assertConsoleOutput("1 null 3");
    }

    @Test(expected = NullPointerException.class)
    public void testPrintNullMessageWithoutArguments() {
        log.print(null);
    }

    @Test(expected = NullPointerException.class)
    public void testPrintNullMessageWithArguments() {
        log.print(null, 1 ,2, 3);
    }

    private void assertConsoleOutput(String expected) {
        assertEquals(
                expected + System.lineSeparator(),
                new String(outputMock.toByteArray(), StandardCharsets.UTF_8));
    }

}