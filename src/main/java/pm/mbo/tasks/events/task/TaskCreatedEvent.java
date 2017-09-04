package pm.mbo.tasks.events.task;

import lombok.Value;
import org.axonframework.serialization.Revision;

@Value
@Revision("1.0")
public class TaskCreatedEvent implements TaskEvent {

    private final String id;

    private final String name;

}
