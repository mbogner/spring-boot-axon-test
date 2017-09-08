package pm.mbo.tasks.domain.task.command;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.serialization.Revision;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.HttpHeaders;
import pm.mbo.tasks.domain.common.command.RestCommand;

import javax.validation.constraints.NotNull;

import static pm.mbo.tasks.domain.common.Revisions.REVISION_1_0;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Revision(REVISION_1_0)
public class CreateTaskCommand extends RestCommand {

    @AggregateIdentifier
    @NotBlank
    private final String id;

    @NotBlank
    private final String name;

    @NotNull
    private final Boolean starred;

    public CreateTaskCommand(final HttpHeaders headers, final String id, final String name, final Boolean starred) {
        super(headers);
        this.id = id;
        this.name = name;
        this.starred = starred;
    }

}
