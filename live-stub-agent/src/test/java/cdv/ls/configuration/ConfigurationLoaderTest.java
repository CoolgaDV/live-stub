package cdv.ls.configuration;

import org.junit.Test;

/**
 * Unit tests for {@link ConfigurationLoader}
 *
 * @author Dmitry Coolga
 *         07.08.2016 21:16
 */
public class ConfigurationLoaderTest {

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyConfigurationLocation() throws Exception {
        new ConfigurationLoader("").load();
    }

}