package pm.mbo.tasks.domain.task.event;

import org.junit.Test;
import pm.mbo.tasks.DataGenerator;
import pm.mbo.tasks.testutil.ValueTestUtil;

public class TaskStarredEventTest {

    @Test
    public void testTaskStarredEvent() throws Exception {
        new ValueTestUtil().validateClass(TaskStarredEvent.class, createRandomEvent());
    }

    public static TaskStarredEvent createRandomEvent() {
        return new TaskStarredEvent(DataGenerator.createRandomString(10), DataGenerator.createRandomBoolean());
    }

}