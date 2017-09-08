package pm.mbo.tasks.rest.task.request;

import org.junit.Test;
import pm.mbo.tasks.DataGenerator;
import pm.mbo.tasks.testutil.BeanTestUtil;

public class CreateTaskRequestTest {

    @Test
    public void testCreateTaskRequest() throws Exception {
        new BeanTestUtil().validateClass(CreateTaskRequest.class, createRandomRequest());
    }

    public static CreateTaskRequest createRandomRequest() {
        return new CreateTaskRequest(DataGenerator.createRandomString(10), DataGenerator.createRandomBoolean());
    }

}