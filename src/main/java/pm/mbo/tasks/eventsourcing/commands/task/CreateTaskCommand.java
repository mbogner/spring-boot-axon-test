package pm.mbo.tasks.eventsourcing.commands.task;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.axonframework.serialization.Revision;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.HttpHeaders;
import pm.mbo.tasks.eventsourcing.commands.RestCommand;

import static pm.mbo.tasks.eventsourcing.Revisions.REVISION_1_0;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Revision(REVISION_1_0)
public class CreateTaskCommand extends RestCommand {

    @NotBlank
    private final String name;

    public CreateTaskCommand(final HttpHeaders headers, final String name) {
        super(headers);
        this.name = name;
    }

}
