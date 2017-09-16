package pm.mbo.tasks.domain.task;

import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Getter
public class Task extends DomainEntity {

    private static final Logger LOG = LoggerFactory.getLogger(Task.class);

    @AggregateIdentifier
    private String id;

    @NotBlank
    private String name;

    @NotNull
    private Boolean starred;

    @CommandHandler
    public Task(final CreateTaskCommand command) {
        CommonCommandHandler.applyCommand(this, command,
                new TaskCreatedEvent(command.getId(), command.getName(), command.getStarred()),
                MetaData.with(MetaDataKey.HTTP_HEADERS, command.getHttpHeaders()));
    }

    @SuppressWarnings("unused")
    @CommandHandler
    public void handleCommand(final UpdateNameCommand command) {
        CommonCommandHandler.applyCommand(this, command,
                new NameUpdatedEvent(command.getId(), command.getName()),
                MetaData.with(MetaDataKey.HTTP_HEADERS, command.getHttpHeaders()));
    }

    @SuppressWarnings("unused")
    @CommandHandler
    public void handleCommand(final StarTaskCommand command) {
        CommonCommandHandler.applyCommand(this, command,
                new TaskStarredEvent(command.getId(), command.getStarred()),
                MetaData.with(MetaDataKey.HTTP_HEADERS, command.getHttpHeaders()));
    }

    @SuppressWarnings("unused")
    @EventSourcingHandler
    private void handleEvent(final TaskCreatedEvent event, final MetaData metadata) {
        CommonEventHandler.applyCommand(this, event, metadata, () -> {
            this.id = event.getId();
            this.name = event.getName();
            this.starred = event.getStarred();
        });
    }

    @SuppressWarnings("unused")
    @EventSourcingHandler
    private void handleEvent(final NameUpdatedEvent event, final MetaData metadata) {
        CommonEventHandler.applyCommand(this, event, metadata, () -> {
            this.name = event.getName();
        });
    }

    @SuppressWarnings("unused")
    @EventSourcingHandler
    private void handleEvent(final TaskStarredEvent event, final MetaData metadata) {
        CommonEventHandler.applyCommand(this, event, metadata, () -> {
            this.starred = event.getStarred();
        });
    }

}

