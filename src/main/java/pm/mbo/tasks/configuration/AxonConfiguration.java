package pm.mbo.tasks.configuration;

import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pm.mbo.tasks.domain.task.Task;

@Configuration
public class AxonConfiguration {

    @Bean
    public Serializer serializer() {
        return new JacksonSerializer();
    }

    @Bean
    public SpringAggregateSnapshotterFactoryBean snapshotter() {
        return new SpringAggregateSnapshotterFactoryBean();
    }

    @Bean
    public SnapshotTriggerDefinition snapshotTriggerDefinition(final Snapshotter snapshotter) throws Exception {
        return new EventCountSnapshotTriggerDefinition(snapshotter, 5);
    }

    @Bean
    public Repository<Task> taskRepository(final EventStore eventStore, final SnapshotTriggerDefinition snapshotTriggerDefinition) {
        return new EventSourcingRepository<>(Task.class, eventStore, snapshotTriggerDefinition);
    }

}