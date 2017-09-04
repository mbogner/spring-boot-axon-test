package pm.mbo.tasks.events.task;

import lombok.Value;
import org.axonframework.serialization.Revision;

@Value
@Revision("1.0")
public class NameUpdatedEvent implements TaskEvent {

    private String id;

    private String name;

}
