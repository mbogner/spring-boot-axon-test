package pm.mbo.tasks.domain.task.event;

import org.junit.Test;
import pm.mbo.tasks.DataGenerator;
import pm.mbo.tasks.testutil.ValueTestUtil;

public class TaskCreatedEventTest {

    @Test
    public void testTaskCreatedEvent() throws Exception {
        new ValueTestUtil().validateClass(TaskCreatedEvent.class, createRandomEvent());
    }

    public static TaskCreatedEvent createRandomEvent() {
        return new TaskCreatedEvent(DataGenerator.createRandomString(10), DataGenerator.createRandomString(10), DataGenerator.createRandomBoolean());
    }

}