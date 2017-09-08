package pm.mbo.tasks.util;

public final class Validator {

    private Validator() {
        throw new IllegalAccessError();
    }

    public static void notNull(final Object obj, final String name) {
        if (null == name || name.isEmpty()) {
            throw new IllegalArgumentException("please provide a name");
        }
        if (null == obj) {
            throw new IllegalArgumentException(String.format("%s must not be null", name));
        }
    }

}
