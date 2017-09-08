package pm.mbo.tasks.rest.task;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pm.mbo.tasks.domain.task.command.CreateTaskCommand;
import pm.mbo.tasks.domain.task.command.StarTaskCommand;
import pm.mbo.tasks.domain.task.command.UpdateNameCommand;
import pm.mbo.tasks.rest.task.request.CreateTaskRequest;
import pm.mbo.tasks.rest.task.request.StarTaskRequest;
import pm.mbo.tasks.rest.task.request.UpdateNameRequest;

import javax.validation.Valid;

import static pm.mbo.tasks.rest.ApiConstant.API_BASE;

@RestController
public class TaskController {

    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);
    public static final String URL = API_BASE + "/tasks";

    private final CommandGateway commandGateway;

    @Autowired
    public TaskController(final CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = URL, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createTask(@RequestHeader final HttpHeaders headers,
                           @RequestBody @Valid final CreateTaskRequest request) {
        LOG.debug("issue {}, headers: {}", request, headers);
        commandGateway.send(new CreateTaskCommand(headers, request.getName(), request.getStarred()));
    }

    @RequestMapping(value = URL + "/{id}/name", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateName(@RequestHeader final HttpHeaders headers,
                           @PathVariable final String id,
                           @RequestBody @Valid final UpdateNameRequest request) {
        LOG.debug("id: {}, issue {}, headers: {}", id, request, headers);
        commandGateway.send(new UpdateNameCommand(headers, id, request.getName()));
    }

    @RequestMapping(value = URL + "/{id}/star", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void starTask(@RequestHeader final HttpHeaders headers,
                         @PathVariable final String id,
                         @RequestBody @Valid final StarTaskRequest request) {
        LOG.debug("id: {}, issue {}, headers: {}", id, request, headers);
        commandGateway.send(new StarTaskCommand(headers, id, request.getStarred()));
    }
}
