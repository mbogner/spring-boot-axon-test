package pm.mbo.tasks;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

@Configuration
public class ApplicationTest {

    private static final String SPRING_STARTUP = "root of context hierarchy";

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void testMain() throws Exception {
        Application.main();
        assertThat(getOutput().contains(SPRING_STARTUP)).isTrue();
    }

    private String getOutput() {
        return this.outputCapture.toString();
    }

}