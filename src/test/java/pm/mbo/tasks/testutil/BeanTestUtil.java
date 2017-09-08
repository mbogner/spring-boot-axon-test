package pm.mbo.tasks.testutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pm.mbo.tasks.Reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

public class BeanTestUtil {

    private static final Logger LOG = LoggerFactory.getLogger(BeanTestUtil.class);

    private static final List<String> DEFAULT_PRIVATE_METHODS = Collections.unmodifiableList(Collections.EMPTY_LIST);
    private static final List<String> DEFAULT_PROTECTED_METHODS = new ArrayList<>(1);
    private static final List<Class<? extends Annotation>> DEFAULT_REQUIRED_ANNOATIONS = Collections.unmodifiableList(Collections.EMPTY_LIST);

    protected static final String METHOD_EQUALS = "equals";
    protected static final String METHOD_TO_STRING = "toString";
    protected static final String METHOD_HASH_CODE = "hashCode";
    protected static final String METHOD_CAN_EQUAL = "canEqual";

    static {
        DEFAULT_PROTECTED_METHODS.add(METHOD_CAN_EQUAL);
    }

    public <T> void validateClass(final Class<T> clazz, final Object instance) {
        assertNotNull(clazz);
        assertNotNull(instance);
        try {
            checkClass(clazz);
            checkClassAnnoations(clazz, getRequiredAnnotations());
            checkDefaultConstructor(clazz);

            final List<Field> fields = Reflection.getDeclaredFieldsOfHierarchy(clazz, new ArrayList<>());
            checkFields(clazz, fields);

            checkFullConstructor(clazz, fields);

            final List<Method> methods = Reflection.getDeclaredMethodsOfHierarchy(clazz, new ArrayList<>());
            checkMethodAccess(clazz, methods);
            checkGetterSetter(instance, fields, methods);

            // check some specific methods

            final Object toStringResult = callMethod(METHOD_TO_STRING, methods, instance);
            assertNotNull(toStringResult);
            assertEquals(toStringResult, callMethod(METHOD_TO_STRING, methods, instance));

            final Object hashCodeResult = callMethod(METHOD_HASH_CODE, methods, instance);
            assertNotNull(hashCodeResult);
            assertEquals(hashCodeResult, callMethod(METHOD_HASH_CODE, methods, instance));

            testEquals(methods, instance);

        } catch (final InstantiationException | IllegalAccessException | InvocationTargetException e) {
            fail(e.getMessage());
        }
    }

    protected <T> void checkClass(final Class<T> clazz) {
        // do nothing
    }

    protected <T> void checkDefaultConstructor(final Class<T> clazz) {
        try {
            clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            fail("bean needs a default constructor");
        }
    }

    protected <T> void checkFullConstructor(final Class<T> clazz, final List<Field> fields) {
        // no special checks here
    }

    protected <T> void checkMethodAccess(final Class<T> clazz, final List<Method> methods) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        assertNotNull(clazz);
        assertNotNull(methods);
        assertThat(methods.size(), greaterThan(0));

        for (final Method method : methods) {
            final String name = String.format("method %s.%s", clazz.getSimpleName(), method.getName());
            if (checkPrivateMethodAccess(method, name)) {
                continue;
            }
            if (checkProtectedMethodAccess(method, name)) {
                continue;
            }
            if (checkGetterSetterMethodAccess(method, name)) {
                continue;
            }
            if (checkHashCodeEqualsToStringMethodAccess(method, name)) {
                continue;
            }
        }
    }

    protected boolean checkPrivateMethodAccess(final Method method, final String name) {
        if (getPrivateMethods().contains(method.getName())) {
            LOG.trace("{} has to be private", name);
            checkPrivate(name, method.getModifiers());
            return true;
        }
        return false;
    }

    protected boolean checkProtectedMethodAccess(final Method method, final String name) {
        if (getProtectedMethods().contains(method.getName())) {
            LOG.trace("{} has to be protected", name);
            checkProtected(name, method.getModifiers());
            return true;
        }
        return false;
    }

    protected boolean checkGetterSetterMethodAccess(final Method method, final String name) {
        if (method.getName().startsWith("set") ||
                method.getName().startsWith("get")) {
            LOG.trace("{} has to be public", name);
            checkPublic(name, method.getModifiers());
            return true;
        }
        return false;
    }

    protected boolean checkHashCodeEqualsToStringMethodAccess(final Method method, final String name) {
        if (method.getName().equals(METHOD_EQUALS) ||
                method.getName().equals(METHOD_TO_STRING) ||
                method.getName().equals(METHOD_HASH_CODE)) {
            LOG.trace("{} has to be public", name);
            checkPublic(name, method.getModifiers());
            return true;
        }
        return false;
    }

    protected <T> void checkFields(final Class<T> clazz, final List<Field> fields) {
        assertNotNull(clazz);
        assertNotNull(fields);
        assertThat(fields.size(), greaterThan(0));

        for (final Field field : fields) {
            final Class<?> type = field.getType();
            LOG.trace("checking field access {}.{}", clazz.getSimpleName(), field.getName());
            final String msg = String.format("field %s.%s", clazz.getSimpleName(), field.getName());
            checkFieldModifier(msg, field.getModifiers());
        }
    }

    protected void checkFieldModifier(final String msg, final int fieldModifier) {
        checkPrivate(msg, fieldModifier);
    }

    protected void checkPrivate(final String name, final int modifiers) {
        assertNotNull(name);
        if (!Modifier.isPrivate(modifiers)) {
            fail(name + " must be private");
        }
    }

    protected void checkPublic(final String name, final int modifiers) {
        assertNotNull(name);
        if (!Modifier.isPublic(modifiers)) {
            fail(name + " must be public");
        }
    }

    protected void checkProtected(final String name, final int modifiers) {
        assertNotNull(name);
        if (!Modifier.isProtected(modifiers)) {
            fail(name + " must be protected");
        }
    }

    protected void checkFinal(final String name, final int modifiers) {
        assertNotNull(name);
        if (!Modifier.isFinal(modifiers)) {
            fail(name + " must be final");
        }
    }

    protected <T> void testEquals(final List<Method> methods, final T instance) throws InvocationTargetException, IllegalAccessException {
        assertNotNull(methods);
        assertNotNull(instance);
        LOG.trace("testing {}.equals", instance.getClass().getSimpleName());
        final Method equals = Reflection.findExistingMethodByNameAndParams(METHOD_EQUALS, methods, Object.class);
        assertTrue((Boolean) equals.invoke(instance, instance));
        assertFalse((Boolean) equals.invoke(instance, new Object[]{null}));
        assertFalse((Boolean) equals.invoke(instance, new Object[]{"asd"}));
    }

    protected <T> Object callMethod(final String name, final List<Method> methods, final T instance) throws InvocationTargetException, IllegalAccessException {
        assertNotNull(name);
        assertNotNull(methods);
        assertNotNull(instance);
        return callMethod(Reflection.findExistingMethodByNameAndParams(name, methods), instance);
    }

    protected <T> Object callMethod(final Method method, final T instance) throws InvocationTargetException, IllegalAccessException {
        assertNotNull(method);
        assertNotNull(instance);
        LOG.trace("testing {}.{}", instance.getClass().getSimpleName(), method.getName());
        if (!Modifier.isPublic(method.getModifiers())) {
            method.setAccessible(true);
        }
        return method.invoke(instance);
    }

    protected <T> void checkGetterSetter(final T instance, final List<Field> fields, final List<Method> methods) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        assertNotNull(instance);
        assertNotNull(fields);
        assertNotNull(methods);
        for (final Field field : fields) {
            if (Modifier.isFinal(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                LOG.trace("ignored static final field {}", field.getName());
                continue;
            }

            final String name = instance.getClass().getSimpleName();
            checkGetter(name, field, methods);
            checkSetter(name, field, methods);
        }
    }

    protected <T> void checkGetter(final String clazzName, final Field field, final List<Method> methods) {
        final String getterName = Reflection.getGetterNameFor(field);
        final String getterSign = String.format("%s %s.%s()", field.getType().getSimpleName(), clazzName, getterName);

        LOG.trace("check existence of getter '{}'", getterSign);
        final Method getterMethod = Reflection.findExistingMethodByNameAndParams(getterName, methods);
        assertEquals(field.getType(), getterMethod.getReturnType());
    }

    protected <T> void checkSetter(final String clazzName, final Field field, final List<Method> methods) {
        final String setterName = Reflection.getSetterNameFor(field);
        final String setterSign = String.format("void %s.%s(%s)", clazzName, setterName, field.getType().getSimpleName());

        LOG.trace("check existence of setter '{}'", setterSign);
        Reflection.findExistingMethodByNameAndParams(setterName, methods, field.getType());
    }

    protected void checkClassAnnoations(final Class<?> clazz, final List<Class<? extends Annotation>> requiredAnnoations) {
        assertNotNull(clazz);
        assertNotNull(requiredAnnoations);
        for (Class<? extends Annotation> requiredAnnoation : requiredAnnoations) {
            LOG.trace("check {} for {}", clazz.getName(), requiredAnnoation.getName());
            assertNotNull(String.format("class %s has to declare @%s", clazz.getName(), requiredAnnoation.getSimpleName()), clazz.getAnnotation(requiredAnnoation));
        }
    }

    protected List<Class<? extends Annotation>> getRequiredAnnotations() {
        return Collections.unmodifiableList(DEFAULT_REQUIRED_ANNOATIONS);
    }

    protected List<String> getPrivateMethods() {
        return DEFAULT_PRIVATE_METHODS;
    }

    protected List<String> getProtectedMethods() {
        return Collections.unmodifiableList(DEFAULT_PROTECTED_METHODS);
    }

}