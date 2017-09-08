package pm.mbo.tasks.domain.task.command;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import pm.mbo.tasks.DataGenerator;
import pm.mbo.tasks.testutil.ValueTestUtil;

public class StarTaskCommandTest {

    @Test
    public void testStarTaskCommand() throws Exception {
        new ValueTestUtil().validateClass(StarTaskCommand.class, createRandomCommand());
    }

    public static StarTaskCommand createRandomCommand() {
        return new StarTaskCommand(new HttpHeaders(), DataGenerator.createRandomString(10), DataGenerator.createRandomBoolean());
    }

}