package cdv.ls.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.zeroturnaround.exec.ProcessExecutor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;

import static cdv.ls.test.SimpleApplicationMethod.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Set of integration tests for applying Live Stub to simple console application.
 *
 * @author Dmitry Coolga
 *         21.08.2016 11:51
 */
public class TestSimpleApplication {

    private static final String DATA_PROVIDER_NAME = "simple-application";

    @DataProvider(name = DATA_PROVIDER_NAME)
    public Object[][] provideTestData() {
        return new Object[][] {

                // Before method tests
                { PUBLIC_INSTANCE,  "before-public-instance-method.xml",    "before-public-instance-method" },
                { PRIVATE_INSTANCE, "before-private-instance-method.xml",   "before-private-instance-method" },
                { PUBLIC_STATIC,    "before-public-static-method.xml",      "before-public-static-method" },
                { PRIVATE_STATIC,   "before-private-static-method.xml",     "before-private-static-method" },

                // After method tests
                { PUBLIC_INSTANCE,  "after-public-instance-method.xml",     "public-instance-method-after" },
                { PRIVATE_INSTANCE, "after-private-instance-method.xml",    "private-instance-method-after" },
                { PUBLIC_STATIC,    "after-public-static-method.xml",       "public-static-method-after" },
                { PRIVATE_STATIC,   "after-private-static-method.xml",      "private-static-method-after" },

                // Method body tests
                { PUBLIC_INSTANCE,  "body-public-instance-method.xml",  "body" },
                { PRIVATE_INSTANCE, "body-private-instance-method.xml", "body" },
                { PUBLIC_STATIC,    "body-public-static-method.xml",    "body" },
                { PRIVATE_STATIC,   "body-private-static-method.xml",   "body" },

                // Complex tests
                { PUBLIC_INSTANCE,  "complex-before-after-method.xml",  "before-public-instance-method-after" },
        };
    }

    @Test(dataProvider = DATA_PROVIDER_NAME)
    public void testSimpleApplication(SimpleApplicationMethod method,
                                      String configurationLocation,
                                      String expectedOutput)
            throws InterruptedException, TimeoutException, IOException {

        assertEquals(runSimpleApplication(method, configurationLocation), expectedOutput);
    }

    @Test
    public void testAfterWithException() throws InterruptedException, IOException, TimeoutException {
        String output = runSimpleApplication(WITH_EXCEPTION, "after-with-exception.xml");
        assertTrue(output.startsWith("finally-after"));
    }

    private String runSimpleApplication(SimpleApplicationMethod method, String configurationLocation)
            throws InterruptedException, TimeoutException, IOException {

        String jar = Paths.get(System.getProperty("build.directory"), System.getProperty("simple.application.jar"))
                .toAbsolutePath()
                .toString();
        String locationPath = Paths.get(System.getProperty("resources.directory"))
                .resolve(configurationLocation)
                .toAbsolutePath()
                .toString();
        String location = "-Dlive-stub.configuration-location=" + locationPath;

        return new ProcessExecutor()
                .directory(new File(System.getProperty("build.directory")))
                .command("java", location, "-javaagent:live-stub.jar", "-jar", jar, method.toString())
                .readOutput(true)
                .execute()
                .outputUTF8();
    }

}
