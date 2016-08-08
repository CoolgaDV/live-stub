package cdv.ls.configuration;

import java.text.MessageFormat;

/**
 * Represents errors in command line arguments and configuration file
 *
 * @author Dmitry Coolga
 *         08.08.2016 06:44
 */
class InvalidConfigurationException extends RuntimeException {

    InvalidConfigurationException(String message) {
        super(message);
    }

    InvalidConfigurationException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }

}
