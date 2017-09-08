package pm.mbo.tasks.testutil;

import org.axonframework.serialization.Revision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pm.mbo.tasks.Reflection;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ValueTestUtil extends BeanTestUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ValueTestUtil.class);

    private static final List<Class<? extends Annotation>> DEFAULT_REQUIRED_ANNOATIONS = new ArrayList<>(1);

    static {
        DEFAULT_REQUIRED_ANNOATIONS.add(Revision.class);
    }

    @Override
    protected <T> void checkDefaultConstructor(final Class<T> clazz) {
        try {
            clazz.getDeclaredConstructor();
            fail(String.format("no default constructor wanted in %s", clazz.getName()));
        } catch (final NoSuchMethodException e) {
            // expected
        }
    }

    @Override
    protected <T> void checkFullConstructor(final Class<T> clazz, final List<Field> fields) {
        Reflection.getDeclaredFieldsOfHierarchy(clazz, new ArrayList<>());
        assertThat(String.format("we only want on full constructor in %s", clazz.getName()), clazz.getDeclaredConstructors().length, equalTo(1));
        final Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
        assertThat(Modifier.isPublic(constructor.getModifiers()), is(true));
        assertThat(constructor.getParameterCount(), equalTo(fields.size()));
    }

    @NotNull
    protected <T> void checkSetter(final String clazzName, final Field field, final List<Method> methods) {
        final String setterName = Reflection.getSetterNameFor(field);
        final String setterSign = String.format("void %s.%s(%s)", clazzName, setterName, field.getType().getSimpleName());

        LOG.trace("check existence of setter '{}'", setterSign);
        assertNull(Reflection.findMethodByNameAndParams(setterName, methods, field.getType()));
    }

    @Override
    protected void checkFieldModifier(final String msg, final int fieldModifier) {
        super.checkFieldModifier(msg, fieldModifier);
        checkFinal(msg, fieldModifier);
    }

    @Override
    protected List<Class<? extends Annotation>> getRequiredAnnotations() {
        return Collections.unmodifiableList(DEFAULT_REQUIRED_ANNOATIONS);
    }
}