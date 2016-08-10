package cdv.ls.configuration;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link CommandLineParameters}
 *
 * @author Dmitry Coolga
 *         07.08.2016 20:35
 */
public class CommandLineParametersTest {

    private static final String CONFIGURATION_LOCATION_STUB = "configuration.xml";

    private static String verboseInitialValue;
    private static String configurationLocationInitialValue;

    @BeforeClass
    public static void saveProperties() {
        verboseInitialValue = System.getProperty(CommandLineParameters.VERBOSE);
        configurationLocationInitialValue = System.getProperty(CommandLineParameters.CONFIGURATION_LOCATION);
    }

    @AfterClass
    public static void recoverProperties() {
        recoverProperty(CommandLineParameters.VERBOSE, verboseInitialValue);
        recoverProperty(CommandLineParameters.CONFIGURATION_LOCATION, configurationLocationInitialValue);
    }

    @Before
    public void setDefaultProperties() {
        System.setProperty(CommandLineParameters.VERBOSE, Boolean.TRUE.toString());
        System.setProperty(CommandLineParameters.CONFIGURATION_LOCATION, CONFIGURATION_LOCATION_STUB);
    }

    @Test
    public void testSuccessfulRead() {
        CommandLineParameters parameters = CommandLineParameters.read();
        assertTrue(parameters.isVerbose());
        assertEquals(CONFIGURATION_LOCATION_STUB, parameters.getConfigurationLocation());
    }

    @Test
    public void testDefaultVerboseValueIsFalse() {
        System.clearProperty(CommandLineParameters.VERBOSE);
        CommandLineParameters parameters = CommandLineParameters.read();
        assertFalse(parameters.isVerbose());
    }

    @Test
    public void testVerboseIsCaseInsensitive() {
        System.setProperty(CommandLineParameters.VERBOSE, "TrUe");
        CommandLineParameters parameters = CommandLineParameters.read();
        assertTrue(parameters.isVerbose());
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testVerboseValueIsInvalid() {
        System.setProperty(CommandLineParameters.VERBOSE, "123");
        CommandLineParameters.read();
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationLocationIsAbsent() {
        System.clearProperty(CommandLineParameters.CONFIGURATION_LOCATION);
        CommandLineParameters.read();
    }

    private static void recoverProperty(String name, String value) {
        if (value == null) {
            System.clearProperty(name);
        } else {
            System.setProperty(name, value);
        }
    }

}