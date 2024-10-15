package eu.easygoit.repository;

import eu.easygoit.enums.IEnumBinaryStatus;
import eu.easygoit.model.Application;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * The interface Application repository.
 */
public interface ApplicationRepository extends JpaPagingAndSortingSAASCodifiableRepository<Application, Long> {

    @Modifying
    @Query("UPDATE Application app SET app.adminStatus = :newStatus WHERE app.id = :id")
    int updateAdminStatusById(@Param("id") Long id,
                              @Param("newStatus") IEnumBinaryStatus.Types newStatus);

    Application findByNameIgnoreCase(String name);
}
