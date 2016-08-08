package cdv.ls.configuration;

import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Unit tests for {@link ConfigurationLoader}
 *
 * @author Dmitry Coolga
 *         07.08.2016 21:16
 */
public class ConfigurationLoaderTest {

    private static final Path TEST_CONFIGURATIONS_DIRECTORY = Paths
            .get(System.getProperty("resourcesDirectory"))
            .resolve("configuration");

    @Test
    public void testConfigurationIsCorrect() throws Exception {
        Configuration configuration = loadConfiguration("correct.xml");
        Assert.assertNotNull(configuration);
        Assert.assertEquals(1, configuration.getClazz().size());

        Class cls = configuration.getClazz().get(0);
        Assert.assertEquals("foo.Bar", cls.getName());
        Assert.assertEquals(1, cls.getMethod().size());

        Method method = cls.getMethod().get(0);
        Assert.assertEquals("baz", method.getName());
        Assert.assertNull(method.getBody());
        Assert.assertEquals("System.out.println(\"Hello !\");", method.getBefore());
        Assert.assertEquals("System.out.println(\"Bye !\");", method.getAfter());
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationLocationIsEmpty() throws Exception {
        new ConfigurationLoader("").load();
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationLocationNotExist() throws Exception {
        loadConfiguration("absent.xml");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationLocationIsDirectory() throws Exception {
        loadConfiguration("");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationXmlIsInvalid() throws Exception {
        loadConfiguration("incorrect.xml");
    }

    private Configuration loadConfiguration(String fileName) throws JAXBException {
        String configurationLoacation = TEST_CONFIGURATIONS_DIRECTORY
                .resolve(fileName)
                .toAbsolutePath()
                .toString();
        return new ConfigurationLoader(configurationLoacation).load();
    }

}