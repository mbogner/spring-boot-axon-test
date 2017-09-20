package pm.mbo.tasks;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

@Configuration
public class ApplicationTest {

    @Test
    public void testMain() throws Exception {
        Application.main();
    }

}