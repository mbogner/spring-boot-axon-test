package pm.mbo.tasks.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pm.mbo.tasks.domain.CommonCommandHandler;
import pm.mbo.tasks.domain.CommonEventHandler;
import pm.mbo.tasks.domain.DefaultCommonCommandHandler;
import pm.mbo.tasks.domain.DefaultCommonEventHandler;

@Configuration
public class DomainHandlerConfig {

    @Bean
    public CommonCommandHandler getCommonCommandHandler() {
        return new DefaultCommonCommandHandler();
    }

    @Bean
    public CommonEventHandler getCommonEventHandler() {
        return new DefaultCommonEventHandler();
    }

}
