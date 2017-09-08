package pm.mbo.tasks.domain.common.command;

import lombok.Data;
import org.springframework.http.HttpHeaders;

import javax.validation.constraints.NotNull;

@Data
public abstract class RestCommand implements Command {

    // stored as Map<String, String>
    @NotNull
    protected final HttpHeaders httpHeaders;

}
