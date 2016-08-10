package cdv.ls.configuration;

import cdv.ls.log.ConsoleLog;
import cdv.ls.log.FakeLog;
import org.junit.Assert;
import org.junit.Test;

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
    public void testConfigurationIsCorrect() {
        Configuration configuration = loadConfiguration("correct.xml");
        Assert.assertNotNull(configuration);
        Assert.assertEquals(1, configuration.getClazz().size());

        Class cls = configuration.getClazz().get(0);
        Assert.assertEquals("foo.Bar", cls.getName());
        Assert.assertEquals(1, cls.getMethod().size());

        Method method = cls.getMethod().get(0);
        Assert.assertEquals("baz", method.getName());
        Assert.assertNull(method.getBody());
        Assert.assertEquals("System.out.println(\"Before\");", method.getBefore());
        Assert.assertEquals("System.out.println(\"After\");", method.getAfter());
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationLocationIsEmpty() {
        new ConfigurationLoader("", new FakeLog()).load();
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationLocationNotExist() {
        loadConfiguration("absent.xml");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationLocationIsDirectory() {
        loadConfiguration("");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationXmlIsInvalid() {
        loadConfiguration("incorrect.xml");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationWithNoClasses() {
        loadConfiguration("no-classes.xml");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationWithNoClassName() {
        loadConfiguration("no-class-name.xml");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationWithNoMethods() {
        loadConfiguration("no-methods.xml");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationWithNoMethodName() {
        loadConfiguration("no-method-name.xml");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationWithEmptyMethod() {
        loadConfiguration("empty-method.xml");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationMethodWithBodyAndAfter() {
        loadConfiguration("method-with-body-and-after.xml");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationMethodWithBodyAndBefore() {
        loadConfiguration("method-with-body-and-before.xml");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationMethodWithEmptyAfter() {
        loadConfiguration("method-with-empty-after.xml");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationMethodWithEmptyBefore() {
        loadConfiguration("method-with-empty-before.xml");
    }

    @Test(expected = InvalidConfigurationException.class)
    public void testConfigurationMethodWithEmptyBody() {
        loadConfiguration("method-with-empty-body.xml");
    }

    private Configuration loadConfiguration(String fileName) {
        String configurationLocation = TEST_CONFIGURATIONS_DIRECTORY
                .resolve(fileName)
                .toAbsolutePath()
                .toString();
        return new ConfigurationLoader(configurationLocation, new FakeLog()).load();
    }

}