package eu.easygoit.repository;

import eu.easygoit.model.RandomKey;

import java.util.Optional;

/**
 * The interface Random key repository.
 */
public interface RandomKeyRepository extends JpaPagingAndSortingSAASRepository<RandomKey, Long> {

    /**
     * Find by domain ignore case and name optional.
     *
     * @param domain the domain
     * @param name   the name
     * @return the optional
     */
    Optional<RandomKey> findByDomainIgnoreCaseAndName(String domain, String name);
}
