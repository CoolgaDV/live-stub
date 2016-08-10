package cdv.ls.log;

/**
 * Interface to write log messages. Common log libraries (slf4j etc) are not used
 * in order not to include additional dependencies and as a matter of fact Live Stub
 * is just an auxiliary low level tool, not a part of user applications.
 *
 * @author Dmitry Coolga
 *         10.08.2016 06:22
 */
public abstract class Log {

    public abstract void print(String message);

    public abstract void print(String message, Object... arguments);

    static void assertMessageNotNull(String message) {
        if (message == null) {
            throw new NullPointerException("Log message should not be null");
        }
    }

}