package cdv.ls.configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Paths;

/**
 * Loads main configuration from external file
 *
 * @author Dmitry Coolga
 *         07.08.2016 20:20
 */
public class ConfigurationLoader {

    private final String configurationLocation;

    public ConfigurationLoader(String configurationLocation) {
        this.configurationLocation = configurationLocation;
    }

    public Configuration load() {
        return parseConfiguration(getConfigurationFile());
    }

    private File getConfigurationFile() {
        if (configurationLocation.trim().isEmpty()) {
            throw new InvalidConfigurationException("Configuration location is empty");
        }
        File file = Paths.get(configurationLocation).toFile();
        if ( ! file.exists()) {
            throw new InvalidConfigurationException("Configuration file ({0}) does not exist", file);
        }
        if (file.isDirectory()) {
            throw new InvalidConfigurationException("Configuration file ({0}) should not be a directory", file);
        }
        return file;
    }

    private Configuration parseConfiguration(File configurationFile) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return Configuration.class.cast(unmarshaller.unmarshal(configurationFile));
        } catch (JAXBException ex) {
            throw new InvalidConfigurationException("Configuration parsing failure (file: {0})", configurationFile);
        }
    }

}
