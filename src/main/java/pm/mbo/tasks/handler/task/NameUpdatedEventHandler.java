package pm.mbo.tasks.handler.task;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.MetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pm.mbo.tasks.domain.task.event.NameUpdatedEvent;

@Component
public class NameUpdatedEventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(NameUpdatedEventHandler.class);

    @EventHandler
    private void handleEvent(final NameUpdatedEvent event, final MetaData metadata) {
        LOG.debug("handle event: {}", event); // TODO do something with the event
    }

}
