package pm.mbo.tasks.domain.common.event;

import org.axonframework.messaging.MetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pm.mbo.tasks.domain.common.DomainEntity;
import pm.mbo.tasks.domain.common.MetaDataKey;

public class DefaultCommonEventHandler implements CommonEventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultCommonEventHandler.class);

    @Override
    public void applyCommand(final DomainEntity aggregate, final Event event, final MetaData metadata, final Runnable runnable) {
        logBeforeApply(aggregate, event, metadata);
        runnable.run();
        logAfterApply(aggregate);
    }

    private void logBeforeApply(final DomainEntity aggregate, final Event event, final MetaData metadata) {
        LOG.debug("### processing event ###");
        LOG.debug("{}: {}", MetaDataKey.HTTP_HEADERS, metadata.get(MetaDataKey.HTTP_HEADERS));
        LOG.debug("before event: {}", aggregate);
        LOG.debug("apply: {}", event);
    }

    private void logAfterApply(final Object aggregate) {
        LOG.debug("after event: {}", aggregate);
    }
}
