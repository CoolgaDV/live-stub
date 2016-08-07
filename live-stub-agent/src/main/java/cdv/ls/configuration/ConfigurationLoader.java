package cdv.ls.configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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

    public Configuration load() throws JAXBException {
        if (configurationLocation.trim().isEmpty()) {
            throw new IllegalArgumentException("Configuration location is empty");
        }
        JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return Configuration.class.cast(unmarshaller.unmarshal(Paths.get(configurationLocation).toFile()));
    }

}
