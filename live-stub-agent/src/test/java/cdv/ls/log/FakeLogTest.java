package cdv.ls.log;

import org.junit.Test;

/**
 * Unit tests for {@link FakeLog}
 *
 * @author Dmitry Coolga
 *         10.08.2016 07:20
 */
public class FakeLogTest {

    private Log log = new FakeLog();

    @Test
    public void testPrintMessageWithNullArgument() {
        log.print("{0} {1} {2}", 1, null, 3);
    }

    @Test(expected = NullPointerException.class)
    public void testPrintNullMessageWithoutArguments() {
        log.print(null);
    }

    @Test(expected = NullPointerException.class)
    public void testPrintNullMessageWithArguments() {
        log.print(null, 1 ,2, 3);
    }

}