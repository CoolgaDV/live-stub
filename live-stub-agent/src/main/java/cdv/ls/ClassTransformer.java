package cdv.ls;

import cdv.ls.configuration.Configuration;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Entry point for methods stubbing
 *
 * @author Dmitry Coolga
 *         07.08.2016 19:17
 */
public class ClassTransformer implements ClassFileTransformer {

    private final Configuration configuration;

    public ClassTransformer(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {
        return new byte[0];
    }

}
