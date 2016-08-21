package cdv.ls;

import cdv.ls.configuration.Class;
import cdv.ls.configuration.Configuration;
import cdv.ls.configuration.Method;
import cdv.ls.log.Log;
import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Optional;

/**
 * Entry point for methods stubbing
 *
 * @author Dmitry Coolga
 *         07.08.2016 19:17
 */
class ClassTransformer implements ClassFileTransformer {

    private final Configuration configuration;

    private final Log log;

    ClassTransformer(Configuration configuration, Log log) {
        this.configuration = configuration;
        this.log = log;
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            java.lang.Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {

        Optional<Class> classOptional = configuration.getClazz().stream()
                .filter(cls -> cls.getName().replace('.', '/').equals(className))
                .findAny();
        try {
            return classOptional.isPresent() ? instrumentClass(classOptional.get()) : null;
        } catch (Exception ex) {
            IllegalClassFormatException exception = new IllegalClassFormatException();
            exception.initCause(ex);
            throw exception;
        }
    }

    private byte[] instrumentClass(Class classToInstrument)
            throws NotFoundException, CannotCompileException, IOException {

        CtClass classBuilder = ClassPool.getDefault().get(classToInstrument.getName());
        for (Method method : classToInstrument.getMethod()) {
            CtMethod methodBuilder = classBuilder.getDeclaredMethod(method.getName());
            log.print("Instrumenting {}::{}", classToInstrument.getName(), method.getName());
            if (method.getBody() != null) {
                methodBuilder.setBody(method.getBody());
            }
            if (method.getBefore() != null) {
                methodBuilder.insertBefore(method.getBefore());
            }
            if (method.getAfter() != null) {
                methodBuilder.insertBefore("try {");
                methodBuilder.insertAfter("} finally {");
                methodBuilder.insertAfter(method.getAfter());
                methodBuilder.insertAfter("}");
            }
        }
        byte[] byteCode = classBuilder.toBytecode();
        classBuilder.detach();
        return byteCode;
    }

}
