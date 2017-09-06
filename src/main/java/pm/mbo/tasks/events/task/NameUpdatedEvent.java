package pm.mbo.tasks.events.task;

import lombok.Value;
import org.axonframework.serialization.Revision;

import static pm.mbo.tasks.events.Revisions.REVISION_1_0;

@Value
@Revision(REVISION_1_0)
public class NameUpdatedEvent implements TaskEvent {

    private String id;

    private String name;

}
