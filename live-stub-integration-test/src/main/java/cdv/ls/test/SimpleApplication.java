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
        METHODS.put(WITH_PARAMETERS, () -> instance.methodWithParameters("abc", 123));
        METHODS.put(WITH_EXCEPTION, instance::methodWithException);
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
        print("public-static-method");
    }

    public void publicInstanceMethod() {
        print("public-instance-method");
    }

    public void methodWithParameters(String first, int second) {
        print("method-with-params:" + first + ":" + second);
    }

    public void methodWithException() {
        throw new RuntimeException("exception");
    }

    private void privateInstanceMethod() {
        print("private-instance-method");
    }

    private static void privateStaticMethod() {
        print("private-static-method");
    }

    private static void print(String message) {
        System.out.print(message);
        System.out.flush();
    }

}
