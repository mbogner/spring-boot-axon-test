package pm.mbo.tasks.eventsourcing.events.task;

import lombok.Value;
import org.axonframework.serialization.Revision;

import static pm.mbo.tasks.eventsourcing.Revisions.REVISION_1_0;

@Value
@Revision(REVISION_1_0)
public class TaskStarredEvent implements TaskEvent {

    private final String id;

    private final Boolean starred;

}
