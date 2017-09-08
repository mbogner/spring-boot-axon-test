package pm.mbo.tasks.domain.common;

import org.junit.Test;
import pm.mbo.tasks.Reflection;

public class RevisionsTest {

    @Test(expected = IllegalAccessError.class)
    public void testRevisions() throws Throwable {
        Reflection.callPrivateDefaultConstructor(Revisions.class);
    }

}