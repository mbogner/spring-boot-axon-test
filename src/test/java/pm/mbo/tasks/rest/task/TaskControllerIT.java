package pm.mbo.tasks.rest.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import pm.mbo.tasks.DataGenerator;
import pm.mbo.tasks.JsonSerializer;
import pm.mbo.tasks.rest.task.request.CreateTaskRequest;
import pm.mbo.tasks.rest.task.request.StarTaskRequest;
import pm.mbo.tasks.rest.task.request.UpdateNameRequest;

import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static pm.mbo.tasks.TestConstants.IT_BASE_URL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerIT {

    private String basePath;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() {
        basePath = String.format("%s%d%s", IT_BASE_URL, port, TaskController.URL);
    }

    public String createTask() throws Exception {
        final URL base = new URL(basePath);
        final CreateTaskRequest rq = new CreateTaskRequest(DataGenerator.createRandomString(10), DataGenerator.createRandomBoolean());

        final ResponseEntity<String> response = template.postForEntity(base.toString(), wrapRq(rq), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(response.getBody(), notNullValue());
        return response.getBody();
    }

    @Test
    public void updateName() throws Exception {
        final String id = createTask();
        final URL base = new URL(String.format("%s/%s/name", basePath, id));
        final UpdateNameRequest rq = new UpdateNameRequest(DataGenerator.createRandomString(10));

        final ResponseEntity<String> response = template.postForEntity(base.toString(), wrapRq(rq), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
    }

    @Test
    public void starTask() throws Exception {
        final String id = createTask();
        final URL base = new URL(String.format("%s/%s/star", basePath, id));
        final StarTaskRequest rq = new StarTaskRequest(DataGenerator.createRandomBoolean());

        final ResponseEntity<String> response = template.postForEntity(base.toString(), wrapRq(rq), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
    }

    private HttpEntity<String> wrapRq(final Object rq) throws JsonProcessingException {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        final String body = JsonSerializer.serialize(rq);
        return new HttpEntity<>(body, headers);
    }

}