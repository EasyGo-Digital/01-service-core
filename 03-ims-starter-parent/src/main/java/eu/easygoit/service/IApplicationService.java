package eu.easygoit.service;

import eu.easygoit.com.rest.service.ICrudServiceMethod;
import eu.easygoit.com.rest.service.IImageServiceMethods;
import eu.easygoit.enums.IEnumBinaryStatus;
import eu.easygoit.model.Application;

/**
 * The interface Application service.
 */
public interface IApplicationService extends ICrudServiceMethod<Long, Application>, IImageServiceMethods<Long, Application> {

    /**
     * Update status application.
     *
     * @param id        the id
     * @param newStatus the new status
     * @return the application
     */
    Application updateStatus(Long id, IEnumBinaryStatus.Types newStatus);

    /**
     * Find by name application.
     *
     * @param name the name
     * @return the application
     */
    Application findByName(String name);
}
