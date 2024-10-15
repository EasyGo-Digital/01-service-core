package eu.easygoit.service.impl;

import eu.easygoit.model.ITLEntity;
import eu.easygoit.service.ITimeLineListenerService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The type Time line listener service.
 */
@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TimeLineListenerService implements ITimeLineListenerService {

    @Override
    public void performPostPersistTL(ITLEntity entity) {

    }

    @Override
    public void performPostRemoveTL(ITLEntity entity) {

    }

    @Override
    public void performPostUpdateTL(ITLEntity entity) {

    }
}

