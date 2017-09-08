package pm.mbo.tasks.eventsourcing.commands.task;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.axonframework.serialization.Revision;
import org.springframework.http.HttpHeaders;
import pm.mbo.tasks.eventsourcing.commands.RestCommand;

import static pm.mbo.tasks.eventsourcing.Revisions.REVISION_1_0;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Revision(REVISION_1_0)
public class UpdateNameCommand extends RestCommand {

    @TargetAggregateIdentifier
    private final String id;

    private final String name;

    public UpdateNameCommand(final HttpHeaders httpHeaders, final String id, final String name) {
        super(httpHeaders);
        this.id = id;
        this.name = name;
    }
}