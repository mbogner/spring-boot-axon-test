package pm.mbo.tasks.domain.task.command;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import pm.mbo.tasks.DataGenerator;
import pm.mbo.tasks.testutil.ValueTestUtil;

public class UpdateNameCommandTest {

    @Test
    public void testUpdateNameCommand() throws Exception {
        new ValueTestUtil().validateClass(UpdateNameCommand.class, createRandomCommand());
    }

    public static UpdateNameCommand createRandomCommand() {
        return new UpdateNameCommand(new HttpHeaders(), DataGenerator.createRandomString(10), DataGenerator.createRandomString(10));
    }

}