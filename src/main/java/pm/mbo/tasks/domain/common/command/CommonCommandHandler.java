package pm.mbo.tasks.domain.common.command;

import org.axonframework.messaging.MetaData;
import pm.mbo.tasks.domain.common.DomainEntity;
import pm.mbo.tasks.domain.common.event.Event;

public interface CommonCommandHandler {

    void applyCommand(final DomainEntity aggregate, final Command command, final Event event, final MetaData metadata);

    void applyCommand(final DomainEntity aggregate, final Command command, final Event event);

}
