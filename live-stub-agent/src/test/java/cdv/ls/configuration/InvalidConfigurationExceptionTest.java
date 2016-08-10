package cdv.ls.configuration;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link InvalidConfigurationException}
 *
 * @author Dmitry Coolga
 *         10.08.2016 07:35
 */
public class InvalidConfigurationExceptionTest {

    @Test
    public void testConstructorWithArguments() {
        InvalidConfigurationException exception = new InvalidConfigurationException("{0} {1} {2}", 1 , 2, 3);
        assertEquals("1 2 3", exception.getMessage());
    }

    @Test
    public void testConstructorWithArgumentsAndCause() {
        Exception cause = new Exception();
        InvalidConfigurationException exception = new InvalidConfigurationException("{0}", 1 , cause);
        assertEquals("1", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

}