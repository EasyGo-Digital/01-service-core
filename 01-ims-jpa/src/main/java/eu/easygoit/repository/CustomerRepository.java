package eu.easygoit.repository;

import eu.easygoit.enums.IEnumBinaryStatus;
import eu.easygoit.model.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * The interface Customer repository.
 */
public interface CustomerRepository extends JpaPagingAndSortingSAASCodifiableRepository<Customer, Long> {

    /**
     * Update admin status by id int.
     *
     * @param id        the id
     * @param newStatus the new status
     * @return the int
     */
    @Modifying
    @Query("UPDATE Customer c SET c.adminStatus = :newStatus WHERE c.id = :id")
    int updateAdminStatusById(@Param("id") Long id,
                              @Param("newStatus") IEnumBinaryStatus.Types newStatus);
}
