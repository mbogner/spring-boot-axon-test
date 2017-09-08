package pm.mbo.tasks.domain;

import org.axonframework.messaging.MetaData;
import pm.mbo.tasks.eventsourcing.commands.Command;
import pm.mbo.tasks.eventsourcing.events.Event;

public interface CommonCommandHandler {

    void applyCommand(final DomainEntity aggregate, final Command command, final Event event, final MetaData metadata);

    void applyCommand(final DomainEntity aggregate, final Command command, final Event event);

}
