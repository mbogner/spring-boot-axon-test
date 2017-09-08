package pm.mbo.tasks.eventsourcing.events.task;

import pm.mbo.tasks.eventsourcing.events.Event;

public interface TaskEvent extends Event {

    String getId();

}
