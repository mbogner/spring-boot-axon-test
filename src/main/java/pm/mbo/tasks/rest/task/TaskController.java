package pm.mbo.tasks.rest.task;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pm.mbo.tasks.commands.task.CreateTaskCommand;
import pm.mbo.tasks.rest.task.request.CreateTaskRequest;

import javax.validation.Valid;

@RestController
public class TaskController {

    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

    private final CommandGateway commandGateway;

    @Autowired
    public TaskController(final CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "/api/tasks", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createTask(@RequestBody @Valid CreateTaskRequest request) {
        Object o = commandGateway.sendAndWait(new CreateTaskCommand(request.getName()));
        LOG.debug("result: {}", o.getClass().getName());
    }
}
