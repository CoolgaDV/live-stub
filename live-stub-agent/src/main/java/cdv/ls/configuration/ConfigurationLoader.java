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
        Configuration configuration = parseConfiguration(getConfigurationFile());
        validateConfiguration(configuration);
        return configuration;
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

    private void validateConfiguration(Configuration configuration) {
        if (configuration.getClazz().isEmpty()) {
            throw new InvalidConfigurationException("Configuration should contain at least one 'class' node");
        }
        for (Class cls : configuration.getClazz()) {
            String className = cls.getName();
            if (className == null || className.trim().isEmpty()) {
                throw new InvalidConfigurationException("Node 'class' should have 'name' attribute");
            }
            if (cls.getMethod().isEmpty()) {
                throw new InvalidConfigurationException(
                        "Node 'class' should have at least one 'method' node (class name: {0})", className);
            }
            for (Method method : cls.getMethod()) {
                String methodName = method.getName();
                if (methodName == null || methodName.trim().isEmpty()) {
                    throw new InvalidConfigurationException(
                            "Node 'method' should have 'name' attribute (class name: {0})", className);
                }
                String body = method.getBody();
                String after = method.getAfter();
                String before = method.getBefore();

                if (before == null && body == null && after == null) {
                    throw new InvalidConfigurationException(
                            "Node 'method' should have at least one node: 'before', 'body' or 'after' " +
                                    "(class name: {0}, method name: {1})", className, methodName);
                }

                checkNodeIsNotEmpty(before, "before", className, methodName);
                checkNodeIsNotEmpty(body, "body", className, methodName);
                checkNodeIsNotEmpty(after, "after", className, methodName);

                if (body != null && (before != null || after != null)) {
                    throw new InvalidConfigurationException(
                            "Node 'body' should not be used with nodes 'before' or 'after' " +
                            "(class name: {0}, method name: {1})", className, methodName);
                }
            }
        }
    }

    private void checkNodeIsNotEmpty(String value, String nodeName, String className, String methodName) {
        if (value != null && value.trim().isEmpty()) {
            throw new InvalidConfigurationException(
                    "Value of node '{0}' should not be empty (class name: {1}, method name: {2})",
                    nodeName, className, methodName);
        }
    }

}
