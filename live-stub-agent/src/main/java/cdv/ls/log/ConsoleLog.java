package cdv.ls.log;

import java.text.MessageFormat;

/**
 * Console {@link Log} implementation. It is used when verbose logging mode is on.
 *
 * @author Dmitry Coolga
 *         10.08.2016 06:22
 */
public class ConsoleLog extends Log {

    @Override
    public void print(String message) {
        assertMessageNotNull(message);
        System.out.println(message);
        System.out.flush();
    }

    @Override
    public void print(String message, Object... arguments) {
        assertMessageNotNull(message);
        System.out.println(MessageFormat.format(message, arguments));
        System.out.flush();
    }

}