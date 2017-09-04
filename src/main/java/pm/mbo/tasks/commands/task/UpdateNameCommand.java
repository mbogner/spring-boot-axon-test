package pm.mbo.tasks.commands.task;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
public class UpdateNameCommand {

    @TargetAggregateIdentifier
    private String id;

    private String name;

}
