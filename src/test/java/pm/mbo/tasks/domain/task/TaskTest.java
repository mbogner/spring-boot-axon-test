package pm.mbo.tasks.domain.task;

import org.axonframework.messaging.MetaData;
import org.junit.Test;
import pm.mbo.tasks.Reflection;
import pm.mbo.tasks.testutil.AggregateTestUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TaskTest {

    @Test
    public void testTask() throws Throwable {
        new AggregateTestUtil().validateClass(Task.class, createRandomAggregate());
    }

    public static Task createRandomAggregate() throws Throwable {
        return Reflection.callPrivateDefaultConstructor(Task.class);
    }

    @Test
    public void testUnknownEvent() throws Throwable {
        final Task task = createRandomAggregate();
        final List<Method> methods = Reflection.getDeclaredMethodsOfHierarchy(Task.class, new ArrayList<>());
        for (final Method method : methods) {
            if (method.getName().equals("handleEvent")
                    && method.getParameterCount() == 2
                    && method.getParameterTypes()[0] == Object.class
                    && method.getParameterTypes()[1] == MetaData.class) {
                method.setAccessible(true);
                method.invoke(task, new Object(), null);
            }
        }
    }

}