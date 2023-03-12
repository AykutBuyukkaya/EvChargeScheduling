package buyukkaya.userservice.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EvDto {
    private UUID id;

    private String name;

    private String plateId;

    private UUID typeId;

}
