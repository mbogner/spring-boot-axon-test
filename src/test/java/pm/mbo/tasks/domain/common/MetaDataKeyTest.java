package pm.mbo.tasks.domain.common;

import org.junit.Test;
import pm.mbo.tasks.Reflection;

public class MetaDataKeyTest {

    @Test(expected = IllegalAccessError.class)
    public void testMetaDataKey() throws Throwable {
        Reflection.callPrivateDefaultConstructor(MetaDataKey.class);
    }

}