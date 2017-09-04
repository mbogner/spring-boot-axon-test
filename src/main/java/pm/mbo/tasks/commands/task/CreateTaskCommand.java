package pm.mbo.tasks.commands.task;

import lombok.Value;
import org.hibernate.validator.constraints.NotBlank;

@Value
public class CreateTaskCommand {

    @NotBlank
    private final String name;

}
