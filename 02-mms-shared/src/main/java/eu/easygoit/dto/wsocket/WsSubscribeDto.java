package eu.easygoit.dto.wsocket;

import eu.easygoit.dto.extendable.AbstractAuditableDto;
import eu.easygoit.enums.IEnumWSBroker;
import eu.easygoit.enums.IEnumWSEndpoint;
import eu.easygoit.enums.IEnumWSStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Ws subscribe dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class WsSubscribeDto extends AbstractAuditableDto<Long> {

    /**
     * The Status.
     */
    @Builder.Default
    IEnumWSStatus.Types status = IEnumWSStatus.Types.DISCONNECTED;
    private String sessionId;
    private Long senderId;
    private Long groupId;
    private IEnumWSEndpoint.Types endPoint;
    private IEnumWSBroker.Types broker;
}
