package pm.mbo.tasks;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public final class Reflection {

    public static <T> Constructor<T> getExistingConstructor(final Class<T> clazz, final Class<?>... params) {
        try {
            return clazz.getDeclaredConstructor(params);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

    public static List<Field> getDeclaredFieldsOfHierarchy(final Class<?> clazz, final List<Field> result) {
        assertNotNull(clazz);
        assertNotNull(result);
        if (clazz == Object.class) {
            return result;
        }
        for (Field field : Arrays.asList(clazz.getDeclaredFields())) {
            if (field.getName().startsWith("$")) {
                continue;
            }
            result.add(field);
        }


        return getDeclaredFieldsOfHierarchy(clazz.getSuperclass(), result);
    }

    public static List<Method> getDeclaredMethodsOfHierarchy(final Class<?> clazz, final List<Method> result) {
        assertNotNull(clazz);
        assertNotNull(result);
        if (clazz == Object.class) {
            return result;
        }
        result.addAll(Arrays.asList(clazz.getDeclaredMethods()));
        return getDeclaredMethodsOfHierarchy(clazz.getSuperclass(), result);
    }

    public static Method findMethodByNameAndParams(final String name, final List<Method> methods, final Class<?>... params) {
        assertNotNull(name);
        assertNotNull(methods);
        assertNotNull(params);
        for (final Method method : methods) {
            if (method.getName().equals(name)
                    && method.getParameterCount() == params.length) {
                if (!compareParams(method, params)) {
                    continue;
                }
                return method;
            }
        }
        return null;
    }

    public static Method findExistingMethodByNameAndParams(final String name, final List<Method> methods, final Class<?>... params) {
        final Method method = findMethodByNameAndParams(name, methods, params);
        assertNotNull(String.format("there should be a method named %s", name), method);
        return method;
    }

    public static boolean compareParams(final Method method, final Class<?>... params) {
        assertNotNull(method);
        assertNotNull(params);
        if (method.getParameterCount() != params.length) {
            return false;
        }
        for (int i = 0; i < method.getParameterCount(); i++) {
            if (method.getParameterTypes()[i] != params[i]) {
                return false;
            }
        }
        return true;
    }

    public static String getSetterNameFor(final Field field) {
        assertNotNull(field);
        return String.format("set%s", StringUtils.capitalize(field.getName()));
    }

    public static String getGetterNameFor(final Field field) {
        assertNotNull(field);
        return String.format("get%s", StringUtils.capitalize(field.getName()));
    }

    public static <T> T callPrivateDefaultConstructor(final Class<T> clazz) throws Throwable {
        final Constructor<T> constructor = clazz.getDeclaredConstructor();
        if (!Modifier.isPrivate(constructor.getModifiers()) &&
                !Modifier.isProtected(constructor.getModifiers())) {
            fail("constructor has to be private or protected");
        }
        constructor.setAccessible(true);
        try {
            return constructor.newInstance();
        } catch (final InstantiationException | IllegalAccessException e) {
            throw e;
        } catch (final InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private Reflection() {
        throw new IllegalAccessError();
    }
}