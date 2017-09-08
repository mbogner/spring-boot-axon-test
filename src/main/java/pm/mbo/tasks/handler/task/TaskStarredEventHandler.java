package pm.mbo.tasks.handler.task;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.MetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pm.mbo.tasks.domain.task.event.TaskStarredEvent;

@Component
public class TaskStarredEventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(TaskStarredEventHandler.class);

    @EventHandler
    private void handleEvent(final TaskStarredEvent event, final MetaData metadata) {
        LOG.debug("handle event: {}", event); // TODO do something with the event
    }

}
