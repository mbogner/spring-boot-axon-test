package pm.mbo.tasks.rest.task.request;

import org.junit.Test;
import pm.mbo.tasks.DataGenerator;
import pm.mbo.tasks.testutil.BeanTestUtil;

public class StarTaskRequestTest {

    @Test
    public void testStarTaskRequest() throws Exception {
        new BeanTestUtil().validateClass(StarTaskRequest.class, createRandomRequest());
    }

    public static StarTaskRequest createRandomRequest() {
        return new StarTaskRequest(DataGenerator.createRandomBoolean());
    }

}