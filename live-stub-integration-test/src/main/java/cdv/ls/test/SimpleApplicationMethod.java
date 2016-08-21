package cdv.ls.test;

/**
 * Methods of {@link SimpleApplication}. This enum is made for convenience
 * to call appropriate methods of {@link SimpleApplication} from integration tests.
 *
 * @author Dmitry Coolga
 *         21.08.2016 13:20
 */
enum SimpleApplicationMethod {
    PUBLIC_STATIC,
    PUBLIC_INSTANCE,
    WITH_PARAMETERS,
    PRIVATE_STATIC,
    PRIVATE_INSTANCE
}
