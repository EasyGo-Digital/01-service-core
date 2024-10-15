package eu.easygoit.repository;

import eu.easygoit.enums.IEnumMsgTemplateName;
import eu.easygoit.model.MsgTemplate;

import java.util.Optional;


/**
 * The interface Msg template repository.
 */
public interface MsgTemplateRepository extends JpaPagingAndSortingSAASCodifiableRepository<MsgTemplate, Long> {

    /**
     * Find by domain ignore case and name optional.
     *
     * @param domain the domain
     * @param name   the name
     * @return the optional
     */
    Optional<MsgTemplate> findByDomainIgnoreCaseAndName(String domain, IEnumMsgTemplateName.Types name);
}
