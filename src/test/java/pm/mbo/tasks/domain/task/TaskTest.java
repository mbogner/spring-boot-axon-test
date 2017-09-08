package pm.mbo.tasks.domain.task;

import org.junit.Test;
import pm.mbo.tasks.Reflection;
import pm.mbo.tasks.testutil.AggregateTestUtil;

public class TaskTest {

    @Test
    public void testTask() throws Throwable {
        new AggregateTestUtil().validateClass(Task.class, createRandomAggregate());
    }

    public static Task createRandomAggregate() throws Throwable {
        return Reflection.callPrivateDefaultConstructor(Task.class);
    }

}