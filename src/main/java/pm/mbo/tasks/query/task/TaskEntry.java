package pm.mbo.tasks.query.task;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import pm.mbo.tasks.query.JpaEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "tasks")
public class TaskEntry implements JpaEntity {

    @Id
    private String id;

    @NotBlank
    private String name;

    @NotNull
    private Boolean starred;

}
