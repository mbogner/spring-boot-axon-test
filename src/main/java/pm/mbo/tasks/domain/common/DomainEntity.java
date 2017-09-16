package pm.mbo.tasks.domain.common;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public abstract class DomainEntity implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(DomainEntity.class);

    @SuppressWarnings("unused")
    @EventSourcingHandler
    private void handleEvent(final Object event, final MetaData metadata) {
        LOG.warn("unprocessed event: {}, metadata: {}", event, metadata);
    }
}
