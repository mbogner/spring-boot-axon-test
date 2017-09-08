package pm.mbo.tasks.domain.task.command;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import pm.mbo.tasks.DataGenerator;
import pm.mbo.tasks.testutil.ValueTestUtil;

public class CreateTaskCommandTest {

    @Test
    public void testCreateTaskCommand() throws Exception {
        new ValueTestUtil().validateClass(CreateTaskCommand.class, createRandomCommand());
    }

    public static CreateTaskCommand createRandomCommand() {
        return new CreateTaskCommand(new HttpHeaders(),
                DataGenerator.createRandomString(10),
                DataGenerator.createRandomString(10),
                DataGenerator.createRandomBoolean());
    }

}