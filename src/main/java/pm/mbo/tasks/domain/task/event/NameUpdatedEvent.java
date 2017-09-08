package pm.mbo.tasks.domain.task.event;

import lombok.Value;
import org.axonframework.serialization.Revision;

import static pm.mbo.tasks.domain.common.Revisions.REVISION_1_0;

@Value
@Revision(REVISION_1_0)
public class NameUpdatedEvent implements TaskEvent {

    private final String id;

    private final String name;

}
