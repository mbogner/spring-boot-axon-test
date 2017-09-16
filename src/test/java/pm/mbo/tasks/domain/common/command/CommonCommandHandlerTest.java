package pm.mbo.tasks.domain.common.command;

import org.junit.Test;
import pm.mbo.tasks.Reflection;

public class CommonCommandHandlerTest {

    @Test(expected = IllegalAccessError.class)
    public void testCommonCommandHandler() throws Throwable {
        Reflection.callPrivateDefaultConstructor(CommonCommandHandler.class);
    }

}