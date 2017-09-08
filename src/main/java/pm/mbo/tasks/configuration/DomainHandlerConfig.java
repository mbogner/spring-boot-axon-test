package pm.mbo.tasks.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pm.mbo.tasks.domain.common.command.CommonCommandHandler;
import pm.mbo.tasks.domain.common.event.CommonEventHandler;
import pm.mbo.tasks.domain.common.command.DefaultCommonCommandHandler;
import pm.mbo.tasks.domain.common.event.DefaultCommonEventHandler;

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
