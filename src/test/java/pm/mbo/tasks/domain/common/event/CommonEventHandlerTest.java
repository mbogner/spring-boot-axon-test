package pm.mbo.tasks.domain.common.event;

import org.junit.Test;
import pm.mbo.tasks.Reflection;

public class CommonEventHandlerTest {

    @Test(expected = IllegalAccessError.class)
    public void testCommonEventHandler() throws Throwable {
        Reflection.callPrivateDefaultConstructor(CommonEventHandler.class);
    }

}