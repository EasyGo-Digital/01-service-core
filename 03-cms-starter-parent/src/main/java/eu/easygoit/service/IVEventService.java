package eu.easygoit.service;

import eu.easygoit.com.rest.service.ICrudServiceMethod;
import eu.easygoit.model.VCalendarEvent;

import java.util.List;

/**
 * The interface Iv event service.
 */
public interface IVEventService extends ICrudServiceMethod<Long, VCalendarEvent> {

    /**
     * Find by domain and calendar list.
     *
     * @param domain   the domain
     * @param calendar the calendar
     * @return the list
     */
    List<VCalendarEvent> findByDomainAndCalendar(String domain, String calendar);
}
