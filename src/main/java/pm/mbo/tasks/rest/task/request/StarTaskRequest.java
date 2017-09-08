package pm.mbo.tasks.rest.task.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StarTaskRequest {

    @NotNull
    private Boolean starred;

}
