package cdv.ls.test;

import org.junit.Test;

/**
 * Integration test set for complex test cases (combination of tags "before", "after" and "body")
 *
 * @author Dmitry Coolga
 *         24.08.2016 07:38
 */
public class TestComplexCases extends TestSimpleApplication {

    @Override
    String getTestConfigurationsDirectory() {
        return "complex";
    }

    @Test
    public void testBeforeAndAfterMethod() {
        assertOutput(
                SimpleApplicationMethod.PUBLIC_INSTANCE,
                "complex-before-after-method.xml",
                "before-public-instance-method-after");
    }

}
