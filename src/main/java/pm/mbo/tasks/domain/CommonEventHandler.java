package pm.mbo.tasks.domain;

import org.axonframework.messaging.MetaData;
import pm.mbo.tasks.eventsourcing.events.Event;

public interface CommonEventHandler {
    void applyCommand(DomainEntity aggregate, Event event, MetaData metadata, Runnable runnable);
}
