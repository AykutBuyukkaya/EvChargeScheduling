package buyukkaya.userservice.domain.model.request;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AddEvRequest {

    private UUID userId;

    private String name;

    private String plateId;

    private UUID typeId;

}
