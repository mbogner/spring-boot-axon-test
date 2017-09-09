package pm.mbo.tasks.query.task;

import org.junit.Test;
import pm.mbo.tasks.DataGenerator;
import pm.mbo.tasks.testutil.BeanTestUtil;

import java.util.UUID;

public class TaskEntryTest {

    @Test
    public void testTaskEntry() throws Exception {
        new BeanTestUtil().validateClass(TaskEntry.class, createRandomEntry());
    }

    public static TaskEntry createRandomEntry() {
        final TaskEntry entry = new TaskEntry();
        entry.setId(UUID.randomUUID().toString());
        entry.setName(DataGenerator.createRandomString(10));
        entry.setStarred(DataGenerator.createRandomBoolean());
        return entry;
    }

}