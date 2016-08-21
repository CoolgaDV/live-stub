package cdv.ls.test;

import org.zeroturnaround.exec.ProcessExecutor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;

/**
 * Set of integration tests for applying Live Stub to simple console application.
 *
 * @author Dmitry Coolga
 *         21.08.2016 11:51
 */
public class TestSimpleApplication {

    private String runSimpleApplication(SimpleApplicationMethod method)
            throws InterruptedException, TimeoutException, IOException {
        Path jar = Paths.get(System.getProperty("build.directory"), System.getProperty("simple.application.jar"));
        return new ProcessExecutor()
                .command("java", "-jar", jar.toAbsolutePath().toString(), method.toString())
                .readOutput(true)
                .execute()
                .outputUTF8();
    }

}
