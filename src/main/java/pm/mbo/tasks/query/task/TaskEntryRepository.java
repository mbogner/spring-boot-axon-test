package pm.mbo.tasks.query.task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskEntryRepository extends JpaRepository<TaskEntry, String> {
}
