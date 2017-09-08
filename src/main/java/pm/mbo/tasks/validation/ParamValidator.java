package pm.mbo.tasks.validation;

public final class ParamValidator {

    private ParamValidator() {
        throw new IllegalAccessError();
    }

    public static void notNull(final Object obj, final String name) {
        if (null == obj) {
            throw new IllegalArgumentException(String.format("%s must not be null", name));
        }
    }

}
