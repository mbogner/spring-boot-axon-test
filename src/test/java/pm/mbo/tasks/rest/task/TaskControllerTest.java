package pm.mbo.tasks.rest.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pm.mbo.tasks.DataGenerator;
import pm.mbo.tasks.JsonSerializer;
import pm.mbo.tasks.rest.task.request.CreateTaskRequest;
import pm.mbo.tasks.rest.task.request.StarTaskRequest;
import pm.mbo.tasks.rest.task.request.UpdateNameRequest;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    public String createTask() throws Exception {
        final String rq = JsonSerializer.serialize(new CreateTaskRequest(DataGenerator.createRandomString(10), DataGenerator.createRandomBoolean()));
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(TaskController.URL)
                .content(rq)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        final String id = result.getResponse().getContentAsString();
        assertNotNull(id);
        return id;
    }

    @Test
    public void updateName() throws Exception {
        final String id = createTask();
        final String rq = JsonSerializer.serialize(new UpdateNameRequest(DataGenerator.createRandomString(10)));
        mvc.perform(MockMvcRequestBuilders
                .post(String.format("%s/%s/name", TaskController.URL, id))
                .content(rq)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void starTask() throws Exception {
        final String id = createTask();
        final String rq = JsonSerializer.serialize(new StarTaskRequest(DataGenerator.createRandomBoolean()));
        mvc.perform(MockMvcRequestBuilders
                .post(String.format("%s/%s/star", TaskController.URL, id))
                .content(rq)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}