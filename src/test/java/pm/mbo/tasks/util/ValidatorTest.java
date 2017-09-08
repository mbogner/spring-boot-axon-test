package pm.mbo.tasks.util;

import org.junit.Test;
import pm.mbo.tasks.Reflection;
import pm.mbo.tasks.TestConstants;

public class ValidatorTest {

    @Test(expected = IllegalAccessError.class)
    public void testValidator() throws Throwable {
        Reflection.callPrivateDefaultConstructor(Validator.class);
    }

    @Test
    public void notNull() throws Exception {
        Validator.notNull(TestConstants.ANY_OBJECT, TestConstants.ANY_STRING);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notNullNullObject() throws Exception {
        Validator.notNull(null, TestConstants.ANY_STRING);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notNullNullName() throws Exception {
        Validator.notNull(TestConstants.ANY_OBJECT, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notNullEmptyName() throws Exception {
        Validator.notNull(TestConstants.ANY_OBJECT, TestConstants.EMPTY_STRING);
    }

}