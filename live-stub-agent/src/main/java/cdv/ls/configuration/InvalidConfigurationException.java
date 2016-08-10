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

    InvalidConfigurationException(String message, Object... arguments) {
        super(MessageFormat.format(message, prepareArguments(arguments)));
        if (hasCause(arguments)) {
            initCause(Throwable.class.cast(arguments[arguments.length - 1]));
        }
    }

    private static Object[] prepareArguments(Object... arguments) {
        if (hasCause(arguments)) {
            Object[] argumentsWithoutCause = new Object[arguments.length - 1];
            System.arraycopy(arguments, 0, argumentsWithoutCause, 0, argumentsWithoutCause.length);
            return argumentsWithoutCause;
        }
        return arguments;
    }

    private static boolean hasCause(Object... arguments) {
        return arguments.length > 0 && arguments[arguments.length - 1] instanceof Throwable;
    }

}
