package pm.mbo.tasks.query.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class TaskEntryRepositoryTest {

    @Autowired
    private TaskEntryRepository taskEntryRepository;

    @Test
    public void findAll() throws Exception {
        assertNotNull(taskEntryRepository.findAll());
    }

}