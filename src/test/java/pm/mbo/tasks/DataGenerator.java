package pm.mbo.tasks;

import org.apache.commons.text.RandomStringGenerator;

import java.math.BigDecimal;
import java.util.*;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

public final class DataGenerator {

    private static final RandomStringGenerator STRING_GENERATOR = new RandomStringGenerator.Builder().withinRange('a', 'z').build();

    public static <T> Object createInstanceOf(final Class<T> type) throws IllegalAccessException, InstantiationException {
        assertNotNull(type);
        if (type.isAssignableFrom(String.class)) {
            return createRandomString(20);
        }
        if (type.isAssignableFrom(BigDecimal.class)) {
            return createRandomBigDecimal();
        }
        if (type.isAssignableFrom(Integer.class)) {
            return createRandomInteger();
        }
        if (type.isAssignableFrom(Double.class)) {
            return createRandomDouble();
        }
        if (type.isAssignableFrom(Long.class)) {
            return createRandomLong();
        }
        if (type.isAssignableFrom(Calendar.class)) {
            return Calendar.getInstance();
        }
        if (type.isAssignableFrom(Date.class)) {
            return Calendar.getInstance().getTime();
        }
        if (type.isAssignableFrom(List.class)) {
            return new ArrayList<>();
        }
        if (type.isAssignableFrom(Set.class)) {
            return new HashSet<>();
        }
        if (type.isAssignableFrom(Map.class)) {
            return new HashMap<>();
        }
        if (type.isEnum()) {
            return createEnumInstanceOf(type, 0);
        }
        return type.newInstance();
    }

    public static String createRandomString(final int length) {
        assertThat(length, greaterThan(0));
        return STRING_GENERATOR.generate(length);
    }

    public static BigDecimal createRandomBigDecimal() {
        return new BigDecimal(String.valueOf(createRandomDouble()));
    }

    public static double createRandomDouble() {
        return new Random().nextDouble();
    }

    public static int createRandomInteger() {
        return new Random().nextInt();
    }

    public static long createRandomLong() {
        return new Random().nextLong();
    }

    public static boolean createRandomBoolean() {
        return new Random().nextBoolean();
    }

    public static Object createEnumInstanceOf(final Class<?> type, final int val) {
        assertNotNull(type);
        assertTrue(type.isEnum());
        @SuppressWarnings("unchecked") final Enum[] constants = ((Class<Enum>) type).getEnumConstants();
        assertThat(constants.length, greaterThan(val));
        return constants[val];
    }

    private DataGenerator() {
        throw new IllegalAccessError();
    }
}