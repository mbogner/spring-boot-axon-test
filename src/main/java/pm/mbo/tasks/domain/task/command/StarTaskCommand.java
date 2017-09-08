package pm.mbo.tasks.domain.task.command;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
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
public class StarTaskCommand extends RestCommand {

    @TargetAggregateIdentifier
    @NotBlank
    private final String id;

    @NotNull
    private final Boolean starred;

    public StarTaskCommand(final HttpHeaders httpHeaders, final String id, final Boolean starred) {
        super(httpHeaders);
        this.id = id;
        this.starred = starred;
    }
}
