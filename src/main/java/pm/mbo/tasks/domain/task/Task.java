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
import pm.mbo.tasks.eventsourcing.commands.task.StarTaskCommand;
import pm.mbo.tasks.eventsourcing.commands.task.UpdateNameCommand;
import pm.mbo.tasks.eventsourcing.events.task.NameUpdatedEvent;
import pm.mbo.tasks.eventsourcing.events.task.TaskCreatedEvent;
import pm.mbo.tasks.eventsourcing.events.task.TaskStarredEvent;

import javax.validation.constraints.NotNull;
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
    private String name;

    @NotNull
    private Boolean starred;

    protected Task() {
        // needed by spring
    }

    @CommandHandler
    public Task(final CreateTaskCommand command) {
        logCommandReceived(this, command);
        apply(new TaskCreatedEvent(IdentifierFactory.getInstance().generateIdentifier(), command.getName(), command.getStarred()),
                MetaData.with(MD_HTTP_HEADERS, command.getHttpHeaders()));
    }

    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    public void handleCommand(final UpdateNameCommand command) {
        logCommandReceived(this, command);
        apply(new NameUpdatedEvent(command.getId(), command.getName()),
                MetaData.with(MD_HTTP_HEADERS, command.getHttpHeaders()));
    }

    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    public void handleCommand(final StarTaskCommand command) {
        logCommandReceived(this, command);
        apply(new TaskStarredEvent(command.getId(), command.getStarred()),
                MetaData.with(MD_HTTP_HEADERS, command.getHttpHeaders()));
    }

    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void handleEvent(final TaskCreatedEvent event, @MetaDataValue(value = MD_HTTP_HEADERS, required = true) final Map<String, String> headers) {
        logBeforeApply(this, event, headers);
        this.id = event.getId();
        this.name = event.getName();
        this.starred = event.getStarred();
        logAfterApply(this);
    }

    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void handleEvent(final NameUpdatedEvent event, @MetaDataValue(value = MD_HTTP_HEADERS, required = true) final Map<String, String> headers) {
        logBeforeApply(this, event, headers);
        this.name = event.getName();
        logAfterApply(this);
    }

    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void handleEvent(final TaskStarredEvent event, @MetaDataValue(value = MD_HTTP_HEADERS, required = true) final Map<String, String> headers) {
        logBeforeApply(this, event, headers);
        this.starred = event.getStarred();
        logAfterApply(this);
    }

    private void logCommandReceived(final Object aggregate, final Object command) {
        LOG.debug("got command: {}, actual state: {}", command, aggregate);
    }

    private void logBeforeApply(final Object aggregate, final Object event, final Object headers) {
        LOG.debug("### processing event ###");
        LOG.debug("headers: {}", headers);
        LOG.debug("before event: {}", aggregate);
        LOG.debug("apply: {}", event);
    }

    private void logAfterApply(final Object aggregate) {
        LOG.debug("after event: {}", aggregate);
    }

}

