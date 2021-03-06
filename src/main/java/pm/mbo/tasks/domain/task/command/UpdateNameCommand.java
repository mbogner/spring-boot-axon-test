package pm.mbo.tasks.domain.task.command;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.axonframework.serialization.Revision;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.HttpHeaders;
import pm.mbo.tasks.domain.common.command.RestCommand;

import static pm.mbo.tasks.domain.common.Revisions.REVISION_1_0;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Revision(REVISION_1_0)
public class UpdateNameCommand extends RestCommand {

    @TargetAggregateIdentifier
    @NotBlank
    private final String id;

    @NotBlank
    private final String name;

    public UpdateNameCommand(final HttpHeaders httpHeaders, final String id, final String name) {
        super(httpHeaders);
        this.id = id;
        this.name = name;
    }
}
