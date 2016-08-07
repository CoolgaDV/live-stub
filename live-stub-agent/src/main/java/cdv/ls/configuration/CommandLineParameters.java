package cdv.ls.configuration;

/**
 * Live Stub command line parameters specified with "-D" prefix
 *
 * @author Dmitry Coolga
 *         07.08.2016 20:22
 */
public class CommandLineParameters {

    static final String VERBOSE = "live-stub.verbose";

    static final String CONFIGURATION_LOCATION = "live-stub.configuration-location";

    public static CommandLineParameters read() {
        boolean verbose = Boolean.valueOf(System.getProperty(VERBOSE, Boolean.FALSE.toString()));
        String configurationLocation = System.getProperty(CONFIGURATION_LOCATION);
        if (configurationLocation == null) {
            throw new IllegalArgumentException("Configuration location was not specified");
        }
        return new CommandLineParameters(verbose, configurationLocation);
    }

    private final boolean verbose;

    private final String configurationLocation;

    private CommandLineParameters(boolean verbose, String configurationLocation) {
        this.verbose = verbose;
        this.configurationLocation = configurationLocation;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public String getConfigurationLocation() {
        return configurationLocation;
    }

}
