package pm.mbo.tasks.domain;

import org.axonframework.messaging.MetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pm.mbo.tasks.eventsourcing.commands.Command;
import pm.mbo.tasks.eventsourcing.events.Event;
import pm.mbo.tasks.validation.ParamValidator;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

public class DefaultCommonCommandHandler implements CommonCommandHandler {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultCommonCommandHandler.class);

    @Override
    public void applyCommand(final DomainEntity aggregate, final Command command, final Event event, final MetaData metadata) {
        validateCommonParams(aggregate, command, event);
        ParamValidator.notNull(metadata, "metadata");
        logCommandReceived(aggregate, command);
        apply(event, metadata);
    }

    @Override
    public void applyCommand(final DomainEntity aggregate, final Command command, final Event event) {
        validateCommonParams(aggregate, command, event);
        logCommandReceived(aggregate, command);
        apply(event);
    }

    private void validateCommonParams(final DomainEntity aggregate, final Command command, final Event event) {
        ParamValidator.notNull(aggregate, "aggregate");
        ParamValidator.notNull(command, "command");
        ParamValidator.notNull(event, "event");
    }

    private void logCommandReceived(final Object aggregate, final Object command) {
        LOG.debug("got command: {}, actual state: {}", command, aggregate);
    }
}
