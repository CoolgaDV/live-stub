package cdv.ls.test;


import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static cdv.ls.test.SimpleApplicationMethod.WITH_EXCEPTION;
import static org.junit.Assert.assertTrue;

/**
 * Integration test set for tag "after"
 *
 * @author Dmitry Coolga
 *         24.08.2016 07:38
 */
public class TestAfterMethodCases extends TestSimpleApplication {

    @Override
    String getTestConfigurationsDirectory() {
        return "after";
    }

    @Test
    public void testPublicInstanceMethod() throws InterruptedException, IOException, TimeoutException {
        assertOutput(
                SimpleApplicationMethod.PUBLIC_INSTANCE,
                "after-public-instance-method.xml",
                "public-instance-method-after");
    }

    @Test
    public void testPrivateInstanceMethod() throws InterruptedException, IOException, TimeoutException {
        assertOutput(
                SimpleApplicationMethod.PRIVATE_INSTANCE,
                "after-private-instance-method.xml",
                "private-instance-method-after");
    }

    @Test
    public void testPublicStaticMethod() throws InterruptedException, IOException, TimeoutException {
        assertOutput(
                SimpleApplicationMethod.PUBLIC_STATIC,
                "after-public-static-method.xml",
                "public-static-method-after");
    }

    @Test
    public void testPrivateStaticMethod() throws InterruptedException, IOException, TimeoutException {
        assertOutput(
                SimpleApplicationMethod.PRIVATE_STATIC,
                "after-private-static-method.xml",
                "private-static-method-after");
    }

    @Test
    public void testMethodWithParameters() throws InterruptedException, IOException, TimeoutException {
        assertOutput(
                SimpleApplicationMethod.WITH_PARAMETERS,
                "after-with-parameters.xml",
                "method-with-params:abc:123-after:abc:123");
    }

    @Test
    public void testAfterWithException() throws InterruptedException, IOException, TimeoutException {
        String output = runSimpleApplication(WITH_EXCEPTION, "after-with-exception.xml");
        assertTrue(output.startsWith("finally-after"));
    }

}
