package pm.mbo.tasks.rest;

import org.junit.Test;
import pm.mbo.tasks.Reflection;

public class ApiConstantTest {

    @Test(expected = IllegalAccessError.class)
    public void testApiConstant() throws Throwable {
        Reflection.callPrivateDefaultConstructor(ApiConstant.class);
    }

}