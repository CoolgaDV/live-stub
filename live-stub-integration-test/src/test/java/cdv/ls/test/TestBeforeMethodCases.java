package cdv.ls.test;

import org.junit.Test;

/**
 * Integration test set for tag "before"
 *
 * @author Dmitry Coolga
 *         24.08.2016 07:37
 */
public class TestBeforeMethodCases extends TestSimpleApplication {

    @Override
    String getTestConfigurationsDirectory() {
        return "before";
    }

    @Test
    public void testPublicInstanceMethod() {
        assertOutput(
                SimpleApplicationMethod.PUBLIC_INSTANCE,
                "before-public-instance-method.xml",
                "before-public-instance-method");
    }

    @Test
    public void testPrivateInstanceMethod() {
        assertOutput(
                SimpleApplicationMethod.PRIVATE_INSTANCE,
                "before-private-instance-method.xml",
                "before-private-instance-method");
    }

    @Test
    public void testPublicStaticMethod() {
        assertOutput(
                SimpleApplicationMethod.PUBLIC_STATIC,
                "before-public-static-method.xml",
                "before-public-static-method");
    }

    @Test
    public void testPrivateStaticMethod() {
        assertOutput(
                SimpleApplicationMethod.PRIVATE_STATIC,
                "before-private-static-method.xml",
                "before-private-static-method");
    }

    @Test
    public void testMethodWithParameters() {
        assertOutput(
                SimpleApplicationMethod.WITH_PARAMETERS,
                "before-with-parameters.xml",
                "method-with-params:def:456");
    }

}
