package cdv.ls;

import cdv.ls.configuration.CommandLineParameters;
import cdv.ls.configuration.Configuration;
import cdv.ls.configuration.ConfigurationLoader;

import javax.xml.bind.JAXBException;
import java.lang.instrument.Instrumentation;

/**
 * Java agent
 *
 * @author Dmitry Coolga
 *         07.08.2016 18:47
 */
public class LiveStubAgent {

    public static void premain(String args, Instrumentation instrumentation) throws JAXBException {
        CommandLineParameters parameters = CommandLineParameters.read();
        Configuration configuration = new ConfigurationLoader(parameters.getConfigurationLocation()).load();
        instrumentation.addTransformer(new ClassTransformer(configuration));
    }

}
