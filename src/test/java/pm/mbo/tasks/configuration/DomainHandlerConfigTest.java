package pm.mbo.tasks.configuration;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DomainHandlerConfigTest {

    @Test
    public void getCommonCommandHandler() throws Exception {
        assertNotNull(new DomainHandlerConfig().getCommonCommandHandler());
    }

    @Test
    public void getCommonEventHandler() throws Exception {
        assertNotNull(new DomainHandlerConfig().getCommonEventHandler());
    }

}