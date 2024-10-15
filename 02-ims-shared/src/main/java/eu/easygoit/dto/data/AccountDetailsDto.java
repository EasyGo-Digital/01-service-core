package eu.easygoit.dto.data;

import eu.easygoit.dto.AddressDto;
import eu.easygoit.dto.ContactDto;
import eu.easygoit.dto.extendable.AbstractAuditableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * The type Account details dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AccountDetailsDto extends AbstractAuditableDto<Long> {

    private String firstName;
    private String lastName;
    private String country;
    private List<ContactDto> contacts;
    private AddressDto address;

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return new StringBuilder()
                .append(this.getFirstName())
                .append(" ")
                .append(this.getLastName())
                .toString();
    }
}
