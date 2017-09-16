package pm.mbo.tasks.rest.task;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.common.IdentifierFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pm.mbo.tasks.domain.task.command.CreateTaskCommand;
import pm.mbo.tasks.domain.task.command.StarTaskCommand;
import pm.mbo.tasks.domain.task.command.UpdateNameCommand;
import pm.mbo.tasks.query.task.TaskEntry;
import pm.mbo.tasks.query.task.TaskEntryRepository;
import pm.mbo.tasks.rest.task.request.CreateTaskRequest;
import pm.mbo.tasks.rest.task.request.StarTaskRequest;
import pm.mbo.tasks.rest.task.request.UpdateNameRequest;

import javax.validation.Valid;

import static pm.mbo.tasks.rest.ApiConstant.API_BASE;

@RestController
@RequestMapping(TaskController.URL)
public class TaskController {

    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);
    public static final String URL = API_BASE + "/tasks";

    private final CommandGateway commandGateway;

    private final TaskEntryRepository taskEntryRepository;

    @Autowired
    public TaskController(final CommandGateway commandGateway,
                          final TaskEntryRepository taskEntryRepository) {
        this.commandGateway = commandGateway;
        this.taskEntryRepository = taskEntryRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public String createTask(@RequestHeader final HttpHeaders headers,
                             @RequestBody @Valid final CreateTaskRequest request) {
        LOG.debug("issue {}, headers: {}", request, headers);
        final String id = IdentifierFactory.getInstance().generateIdentifier();
        commandGateway.send(new CreateTaskCommand(headers, id, request.getName(), request.getStarred()));
        return id;
    }

    @RequestMapping(value = "{id}/name", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateName(@RequestHeader final HttpHeaders headers,
                           @PathVariable final String id,
                           @RequestBody @Valid final UpdateNameRequest request) {
        LOG.debug("id: {}, issue {}, headers: {}", id, request, headers);
        commandGateway.send(new UpdateNameCommand(headers, id, request.getName()));
    }

    @RequestMapping(value = "{id}/star", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void starTask(@RequestHeader final HttpHeaders headers,
                         @PathVariable final String id,
                         @RequestBody @Valid final StarTaskRequest request) {
        LOG.debug("id: {}, issue {}, headers: {}", id, request, headers);
        commandGateway.send(new StarTaskCommand(headers, id, request.getStarred()));
    }

    // pageable: ?page=0&limit=20&sort=name,desc&sort=id,asc
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Page<TaskEntry> findAll(@RequestHeader final HttpHeaders headers, final Pageable pageable) {
        LOG.debug("findAll: {}, headers: {}", pageable, headers);
        return taskEntryRepository.findAll(pageable);
    }

}
