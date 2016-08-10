package cdv.ls.log;

/**
 * No operation {@link Log} implementation. It is used when verbose logging mode is off.
 *
 * @author Dmitry Coolga
 *         10.08.2016 06:22
 */
public class FakeLog extends Log {

    @Override
    public void print(String message) {
        assertMessageNotNull(message);
    }

    @Override
    public void print(String message, Object... arguments) {
        assertMessageNotNull(message);
    }

}