package pm.mbo.tasks.configuration;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AxonConfigurationTest {

    @Test
    public void getSerializer() throws Exception {
        assertNotNull(new AxonConfiguration().serializer());
    }

}