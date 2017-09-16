package pm.mbo.tasks.domain.common.command;

import org.axonframework.messaging.MetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pm.mbo.tasks.domain.common.DomainEntity;
import pm.mbo.tasks.domain.common.event.Event;
import pm.mbo.tasks.util.Validator;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

public class CommonCommandHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CommonCommandHandler.class);

    public static void applyCommand(final DomainEntity aggregate, final Command command, final Event event, final MetaData metadata) {
        validateCommonParams(aggregate, command, event);
        Validator.notNull(metadata, "metadata");
        logCommandReceived(aggregate, command);
        apply(event, metadata);
    }

    private static void validateCommonParams(final DomainEntity aggregate, final Command command, final Event event) {
        Validator.notNull(aggregate, "aggregate");
        Validator.notNull(command, "command");
        Validator.notNull(event, "event");
    }

    private static void logCommandReceived(final Object aggregate, final Object command) {
        LOG.debug("got command: {}, actual state: {}", command, aggregate);
    }

    private CommonCommandHandler() {
        throw new IllegalAccessError();
    }
}
