package pm.mbo.tasks.domain.common.event;

import org.axonframework.messaging.MetaData;
import pm.mbo.tasks.domain.common.DomainEntity;

public interface CommonEventHandler {
    void applyCommand(DomainEntity aggregate, Event event, MetaData metadata, Runnable runnable);
}
