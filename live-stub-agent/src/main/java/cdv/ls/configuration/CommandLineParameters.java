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
        String verbose = System.getProperty(VERBOSE, Boolean.FALSE.toString()).toLowerCase();
        if ( ! Boolean.FALSE.toString().equals(verbose) && ! Boolean.TRUE.toString().equals(verbose)) {
            throw new InvalidConfigurationException("Value of property '{0}' is incorrect. " +
                    "It should be case insensitive 'true' or 'false'", VERBOSE);
        }
        String configurationLocation = System.getProperty(CONFIGURATION_LOCATION);
        if (configurationLocation == null) {
            throw new InvalidConfigurationException("Property '{0}' was not specified", CONFIGURATION_LOCATION);
        }
        return new CommandLineParameters(Boolean.valueOf(verbose), configurationLocation);
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
