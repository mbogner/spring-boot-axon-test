package pm.mbo.tasks.rest.task.request;

import org.junit.Test;
import pm.mbo.tasks.DataGenerator;
import pm.mbo.tasks.testutil.BeanTestUtil;

public class UpdateNameRequestTest {

    @Test
    public void testUpdateNameRequest() throws Exception {
        new BeanTestUtil().validateClass(UpdateNameRequest.class, createRandomRequest());
    }

    public static UpdateNameRequest createRandomRequest() {
        return new UpdateNameRequest(DataGenerator.createRandomString(10));
    }

}