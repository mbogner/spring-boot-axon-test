package pm.mbo.tasks.configuration;

import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfiguration {

    @Bean
    public Serializer getSerializer() {
        return new JacksonSerializer();
    }

}
