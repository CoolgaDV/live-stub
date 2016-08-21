package cdv.ls.test;

import org.junit.Assert;
import org.junit.Test;
import org.zeroturnaround.exec.ProcessExecutor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;

/**
 * Set of integration tests for applying Live Stub to simple console application.
 *
 * @author Dmitry Coolga
 *         21.08.2016 11:51
 */
public class TestSimpleApplication {

    @Test
    public void testBeforePublicInstanceMethod() throws InterruptedException, IOException, TimeoutException {
        String output = runSimpleApplication(
                SimpleApplicationMethod.PUBLIC_INSTANCE,
                "before-public-instance-method.xml");
        Assert.assertEquals("before-public-instance-method", output);
    }

    @Test
    public void testBeforePrivateInstanceMethod() throws InterruptedException, IOException, TimeoutException {
        String output = runSimpleApplication(
                SimpleApplicationMethod.PRIVATE_INSTANCE,
                "before-private-instance-method.xml");
        Assert.assertEquals("before-private-instance-method", output);
    }

    @Test
    public void testBeforePublicStaticMethod() throws InterruptedException, IOException, TimeoutException {
        String output = runSimpleApplication(
                SimpleApplicationMethod.PUBLIC_STATIC,
                "before-public-static-method.xml");
        Assert.assertEquals("before-public-static-method", output);
    }

    @Test
    public void testBeforePrivateStaticMethod() throws InterruptedException, IOException, TimeoutException {
        String output = runSimpleApplication(
                SimpleApplicationMethod.PRIVATE_STATIC,
                "before-private-static-method.xml");
        Assert.assertEquals("before-private-static-method", output);
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
