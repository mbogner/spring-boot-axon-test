package pm.mbo.tasks.domain.task.event;

import lombok.Value;
import org.axonframework.serialization.Revision;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

import static pm.mbo.tasks.domain.common.Revisions.REVISION_1_0;

@Value
@Revision(REVISION_1_0)
public class TaskStarredEvent implements TaskEvent {

    @NotBlank
    private final String id;

    @NotNull
    private final Boolean starred;

}
