package pm.mbo.tasks.rest.task.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskRequest {

    @NotBlank
    private String name;

    @NotNull
    private Boolean starred;

}
