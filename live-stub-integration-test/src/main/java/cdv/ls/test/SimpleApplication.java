package cdv.ls.test;

import java.util.HashMap;
import java.util.Map;

import static cdv.ls.test.SimpleApplicationMethod.*;

/**
 * Simple console application for integration testing of Live Stub.
 *
 * @author Dmitry Coolga
 *         20.08.2016 08:57
 */
public class SimpleApplication {

    private static final Map<SimpleApplicationMethod, Runnable> METHODS = new HashMap<>();

    static {
        SimpleApplication instance = new SimpleApplication();
        METHODS.put(PUBLIC_STATIC, SimpleApplication::publicStaticMethod);
        METHODS.put(PUBLIC_INSTANCE, instance::publicInstanceMethod);
        METHODS.put(PRIVATE_STATIC, SimpleApplication::privateStaticMethod);
        METHODS.put(PRIVATE_INSTANCE, instance::privateInstanceMethod);
        METHODS.put(WITH_PARAMETERS, () -> instance.methodWithParams("abc", 123));
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                    "There should be one argument - method to be run. Actual arguments number is " + args.length);
        }
        SimpleApplicationMethod method = SimpleApplicationMethod.valueOf(args[0]);
        METHODS.get(method).run();
    }

    public static void publicStaticMethod() {
        print("publicStaticMethod");
    }

    public void publicInstanceMethod() {
        print("publicInstanceMethod");
    }

    public void methodWithParams(String first, int second) {
        print("methodWithParams:" + first + ":" + second);
    }

    private void privateInstanceMethod() {
        print("privateInstanceMethod");
    }

    private static void privateStaticMethod() {
        print("privateStaticMethod");
    }

    private static void print(String message) {
        System.out.print(message);
        System.out.flush();
    }

}
