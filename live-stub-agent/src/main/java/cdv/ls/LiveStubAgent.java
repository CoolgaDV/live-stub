package cdv.ls;

import cdv.ls.configuration.CommandLineParameters;
import cdv.ls.configuration.Configuration;
import cdv.ls.configuration.ConfigurationLoader;
import cdv.ls.log.ConsoleLog;
import cdv.ls.log.FakeLog;
import cdv.ls.log.Log;

import java.lang.instrument.Instrumentation;

/**
 * Java agent
 *
 * @author Dmitry Coolga
 *         07.08.2016 18:47
 */
public class LiveStubAgent {

    public static void premain(String args, Instrumentation instrumentation) {

        CommandLineParameters parameters = CommandLineParameters.read();
        String configurationLocation = parameters.getConfigurationLocation();
        Log log = parameters.isVerbose() ? new ConsoleLog() : new FakeLog();
        log.print("Live stub is active. Configuration file location: {0}", configurationLocation);

        Configuration configuration = new ConfigurationLoader(configurationLocation, log).load();

        instrumentation.addTransformer(new ClassTransformer(configuration, log));
        log.print("Bytecode transformer is registered");
    }

}
