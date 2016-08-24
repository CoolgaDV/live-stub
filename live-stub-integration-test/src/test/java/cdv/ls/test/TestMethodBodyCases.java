package cdv.ls.test;

import org.junit.Test;

/**
 * Integration test set for tag "body"
 *
 * @author Dmitry Coolga
 *         24.08.2016 07:38
 */
public class TestMethodBodyCases extends TestSimpleApplication {

    @Override
    String getTestConfigurationsDirectory() {
        return "body";
    }

    @Test
    public void testPublicInstanceMethod() {
        assertOutput(
                SimpleApplicationMethod.PUBLIC_INSTANCE,
                "body-public-instance-method.xml",
                "body");
    }

    @Test
    public void testPrivateInstanceMethod() {
        assertOutput(
                SimpleApplicationMethod.PRIVATE_INSTANCE,
                "body-private-instance-method.xml",
                "body");
    }

    @Test
    public void testPublicStaticMethod() {
        assertOutput(
                SimpleApplicationMethod.PUBLIC_STATIC,
                "body-public-static-method.xml",
                "body");
    }

    @Test
    public void testPrivateStaticMethod() {
        assertOutput(
                SimpleApplicationMethod.PRIVATE_STATIC,
                "body-private-static-method.xml",
                "body");
    }

    @Test
    public void testMethodWithParameters() {
        assertOutput(
                SimpleApplicationMethod.WITH_PARAMETERS,
                "body-with-parameters.xml",
                "body:abc:123");
    }

    @Test
    public void testCDataUsage() {
        assertOutput(
                SimpleApplicationMethod.PUBLIC_INSTANCE,
                "body-with-cdata.xml",
                "<&body&>");
    }

}
