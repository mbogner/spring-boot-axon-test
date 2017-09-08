package pm.mbo.tasks.domain.task;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.common.IdentifierFactory;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.spring.stereotype.Aggregate;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pm.mbo.tasks.domain.common.DomainEntity;
import pm.mbo.tasks.domain.common.MetaDataKey;
import pm.mbo.tasks.domain.common.command.CommonCommandHandler;
import pm.mbo.tasks.domain.common.event.CommonEventHandler;
import pm.mbo.tasks.domain.task.command.CreateTaskCommand;
import pm.mbo.tasks.domain.task.command.StarTaskCommand;
import pm.mbo.tasks.domain.task.command.UpdateNameCommand;
import pm.mbo.tasks.domain.task.event.NameUpdatedEvent;
import pm.mbo.tasks.domain.task.event.TaskCreatedEvent;
import pm.mbo.tasks.domain.task.event.TaskStarredEvent;

import javax.validation.constraints.NotNull;

@Aggregate
@ToString
@EqualsAndHashCode
@Getter
public class Task implements DomainEntity {

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
    public Task(final CreateTaskCommand command, final CommonCommandHandler commandHandler) {
        commandHandler.applyCommand(this, command,
                new TaskCreatedEvent(IdentifierFactory.getInstance().generateIdentifier(), command.getName(), command.getStarred()),
                MetaData.with(MetaDataKey.HTTP_HEADERS, command.getHttpHeaders()));
    }

    @CommandHandler
    public void handleCommand(final UpdateNameCommand command, final CommonCommandHandler commandHandler) {
        commandHandler.applyCommand(this, command,
                new NameUpdatedEvent(command.getId(), command.getName()),
                MetaData.with(MetaDataKey.HTTP_HEADERS, command.getHttpHeaders()));
    }

    @CommandHandler
    public void handleCommand(final StarTaskCommand command, final CommonCommandHandler commandHandler) {
        commandHandler.applyCommand(this, command,
                new TaskStarredEvent(command.getId(), command.getStarred()),
                MetaData.with(MetaDataKey.HTTP_HEADERS, command.getHttpHeaders()));
    }

    @EventSourcingHandler
    private void handleEvent(final TaskCreatedEvent event, final MetaData metadata, final CommonEventHandler eventHandler) {
        eventHandler.applyCommand(this, event, metadata, () -> {
            this.id = event.getId();
            this.name = event.getName();
            this.starred = event.getStarred();
        });
    }

    @EventSourcingHandler
    private void handleEvent(final NameUpdatedEvent event, final MetaData metadata, final CommonEventHandler eventHandler) {
        eventHandler.applyCommand(this, event, metadata, () -> {
            this.name = event.getName();
        });
    }

    @EventSourcingHandler
    private void handleEvent(final TaskStarredEvent event, final MetaData metadata, final CommonEventHandler eventHandler) {
        eventHandler.applyCommand(this, event, metadata, () -> {
            this.starred = event.getStarred();
        });
    }

}

