package pm.mbo.tasks.query.task;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.MetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pm.mbo.tasks.domain.task.event.NameUpdatedEvent;
import pm.mbo.tasks.domain.task.event.TaskCreatedEvent;
import pm.mbo.tasks.domain.task.event.TaskStarredEvent;

@Component
public class TaskEntryUpdatingEventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(TaskEntryUpdatingEventHandler.class);

    private final TaskEntryRepository taskEntryRepository;

    @Autowired
    public TaskEntryUpdatingEventHandler(final TaskEntryRepository taskEntryRepository) {
        this.taskEntryRepository = taskEntryRepository;
    }

    @EventHandler
    private void handleEvent(final TaskCreatedEvent event, final MetaData metadata) {
        LOG.debug("handle event: {}", event);

        final TaskEntry entry = new TaskEntry();
        entry.setId(event.getId());
        entry.setName(event.getName());
        entry.setStarred(event.getStarred());

        taskEntryRepository.save(entry);
    }

    @EventHandler
    private void handleEvent(final NameUpdatedEvent event, final MetaData metadata) {
        LOG.debug("handle event: {}", event);

        final TaskEntry entry = taskEntryRepository.findOne(event.getId());
        if (null != entry) {
            entry.setName(event.getName());
            taskEntryRepository.save(entry);
        }
    }

    @EventHandler
    private void handleEvent(final TaskStarredEvent event, final MetaData metadata) {
        LOG.debug("handle event: {}", event);

        final TaskEntry entry = taskEntryRepository.findOne(event.getId());
        if (null != entry) {
            entry.setStarred(event.getStarred());
            taskEntryRepository.save(entry);
        }
    }
}
