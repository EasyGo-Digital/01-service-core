package eu.easygoit.repository;

import eu.easygoit.annotation.IgnoreRepository;
import eu.easygoit.model.MailMessage;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

/**
 * The interface Mail message repository.
 */
@IgnoreRepository
public interface MailMessageRepository extends CassandraRepository<MailMessage, Long> {

    /**
     * Find all by domain ignore case list.
     *
     * @param domain the domain
     * @return the list
     */
    @AllowFiltering
    List<MailMessage> findAllByDomainIgnoreCase(String domain);
}
