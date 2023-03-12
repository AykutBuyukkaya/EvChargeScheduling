package buyukkaya.userservice.domain.model.response;

import buyukkaya.userservice.domain.model.dto.EvDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class AddEvResponse extends BaseResponse{

    private EvDto ev;

}
