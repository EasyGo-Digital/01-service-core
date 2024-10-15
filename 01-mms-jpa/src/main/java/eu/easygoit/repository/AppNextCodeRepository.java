package eu.easygoit.repository;

import eu.easygoit.model.AppNextCode;
import org.springframework.stereotype.Repository;


/**
 * The interface App next code repository.
 */
@Repository
public interface AppNextCodeRepository extends NextCodeRepository<AppNextCode, Long> {

}
