package pm.mbo.tasks.handler.task;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.MetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pm.mbo.tasks.domain.task.event.TaskCreatedEvent;

@Component
public class TaskCreatedEventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(TaskCreatedEventHandler.class);

    @EventHandler
    private void handleEvent(final TaskCreatedEvent event, final MetaData metadata) {
        LOG.debug("handle event: {}", event); // TODO do something with the event
    }

}
