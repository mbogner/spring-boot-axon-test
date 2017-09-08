package pm.mbo.tasks.eventsourcing.events.task;

import java.io.Serializable;

public interface TaskEvent extends Serializable {

    String getId();

}
