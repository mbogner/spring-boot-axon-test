package pm.mbo.tasks.domain.task;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.common.IdentifierFactory;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.messaging.annotation.MetaDataValue;
import org.axonframework.spring.stereotype.Aggregate;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pm.mbo.tasks.eventsourcing.commands.task.CreateTaskCommand;
import pm.mbo.tasks.eventsourcing.commands.task.UpdateNameCommand;
import pm.mbo.tasks.eventsourcing.events.task.NameUpdatedEvent;
import pm.mbo.tasks.eventsourcing.events.task.TaskCreatedEvent;

import javax.persistence.Column;
import java.util.Map;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@ToString
@EqualsAndHashCode
@Getter
public class Task {

    private static final String MD_HTTP_HEADERS = "http_headers";

    private static final Logger LOG = LoggerFactory.getLogger(Task.class);

    @AggregateIdentifier
    private String id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @CommandHandler
    public Task(final CreateTaskCommand command) {
        LOG.info("got command: {}", command);
        apply(new TaskCreatedEvent(IdentifierFactory.getInstance().generateIdentifier(), command.getName()),
                MetaData.with(MD_HTTP_HEADERS, command.getHttpHeaders()));
    }

    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    public void handleCommand(final UpdateNameCommand command) {
        LOG.info("got command: {}", command);
        apply(new NameUpdatedEvent(command.getId(), command.getName()),
                MetaData.with(MD_HTTP_HEADERS, command.getHttpHeaders()));
    }

    @SuppressWarnings("UnusedDeclaration")
    protected Task() {
        // needed by jpa
    }

    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void handleEvent(final TaskCreatedEvent event, @MetaDataValue(value = MD_HTTP_HEADERS, required = true) final Map<String, String> headers) {
        LOG.info("apply event: {}, headers: {}", event, headers);
        this.id = event.getId();
        this.name = event.getName();
    }

    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void handleEvent(final NameUpdatedEvent event, @MetaDataValue(value = MD_HTTP_HEADERS, required = true) final Map<String, String> headers) {
        LOG.info("apply event: {}, headers: {}", event, headers);
        this.name = event.getName();
    }

}

