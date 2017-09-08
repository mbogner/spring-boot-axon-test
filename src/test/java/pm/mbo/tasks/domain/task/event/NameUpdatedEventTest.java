package pm.mbo.tasks.domain.task.event;

import org.junit.Test;
import pm.mbo.tasks.DataGenerator;
import pm.mbo.tasks.testutil.ValueTestUtil;

public class NameUpdatedEventTest {

    @Test
    public void testNameUpdatedEvent() throws Exception {
        new ValueTestUtil().validateClass(NameUpdatedEvent.class, createRandomEvent());
    }

    public static NameUpdatedEvent createRandomEvent() {
        return new NameUpdatedEvent(DataGenerator.createRandomString(10), DataGenerator.createRandomString(10));
    }

}