package pm.mbo.tasks.domain.task.event;

import lombok.Value;
import org.axonframework.serialization.Revision;
import org.hibernate.validator.constraints.NotBlank;

import static pm.mbo.tasks.domain.common.Revisions.REVISION_1_0;

@Value
@Revision(REVISION_1_0)
public class NameUpdatedEvent implements TaskEvent {

    @NotBlank
    private final String id;

    @NotBlank
    private final String name;

}
