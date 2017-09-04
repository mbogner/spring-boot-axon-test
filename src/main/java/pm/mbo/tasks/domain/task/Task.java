package pm.mbo.tasks.domain.task;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.common.IdentifierFactory;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pm.mbo.tasks.commands.task.CreateTaskCommand;
import pm.mbo.tasks.events.task.TaskCreatedEvent;

import javax.persistence.Column;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@ToString
@EqualsAndHashCode
@Getter
public class Task {

    private static final Logger LOG = LoggerFactory.getLogger(Task.class);

    @AggregateIdentifier
    private String id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @CommandHandler
    public Task(final CreateTaskCommand command) {
        LOG.info("got command to create new task");
        apply(new TaskCreatedEvent(IdentifierFactory.getInstance().generateIdentifier(), command.getName()));
    }

    @SuppressWarnings("UnusedDeclaration")
    protected Task() {
        // needed by jpa
    }

    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void handleTaskCreatedEvent(final TaskCreatedEvent event) {
        LOG.info("processing create new task event");
        this.id = event.getId();
        this.name = event.getName();
    }

}

