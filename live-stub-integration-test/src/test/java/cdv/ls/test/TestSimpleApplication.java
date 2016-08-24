package cdv.ls.test;

import org.zeroturnaround.exec.ProcessExecutor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;

/**
 * Basic class for integration tests of applying Live Stub to simple console application.
 *
 * @author Dmitry Coolga
 *         21.08.2016 11:51
 */
abstract class TestSimpleApplication {

    abstract String getTestConfigurationsDirectory();

    void assertOutput(SimpleApplicationMethod method, String configurationFile, String expectedOutput) {
        assertEquals(expectedOutput, runSimpleApplication(method, configurationFile));
    }

    String runSimpleApplication(SimpleApplicationMethod method, String configurationFile) {
        try {
            String jar = Paths.get(System.getProperty("build.directory"))
                    .resolve(System.getProperty("simple.application.jar"))
                    .toAbsolutePath()
                    .toString();
            String locationPath = Paths.get(System.getProperty("resources.directory"))
                    .resolve(getTestConfigurationsDirectory())
                    .resolve(configurationFile)
                    .toAbsolutePath()
                    .toString();
            String location = "-Dlive-stub.configuration-location=" + locationPath;

            return new ProcessExecutor()
                    .directory(new File(System.getProperty("build.directory")))
                    .command("java", location, "-javaagent:live-stub.jar", "-jar", jar, method.toString())
                    .readOutput(true)
                    .execute()
                    .outputUTF8();
        } catch (InterruptedException | TimeoutException | IOException ex) {
            throw new RuntimeException("Simple application execution failure", ex);
        }
    }

}
