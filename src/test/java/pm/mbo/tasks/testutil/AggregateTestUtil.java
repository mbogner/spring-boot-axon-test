package pm.mbo.tasks.testutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pm.mbo.tasks.Reflection;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.assertNull;

public class AggregateTestUtil extends BeanTestUtil {

    private static final Logger LOG = LoggerFactory.getLogger(AggregateTestUtil.class);

    @Override
    protected <T> void checkDefaultConstructor(final Class<T> clazz) {
        final Constructor<T> constructor = Reflection.getExistingConstructor(clazz);
        checkProtected("default constructor", constructor.getModifiers());
    }

    @NotNull
    protected <T> void checkSetter(final String clazzName, final Field field, final List<Method> methods) {
        final String setterName = Reflection.getSetterNameFor(field);
        final String setterSign = String.format("void %s.%s(%s)", clazzName, setterName, field.getType().getSimpleName());

        LOG.trace("check existence of setter '{}'", setterSign);
        assertNull(Reflection.findMethodByNameAndParams(setterName, methods, field.getType()));
    }

}