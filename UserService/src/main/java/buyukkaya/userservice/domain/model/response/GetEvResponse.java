package buyukkaya.userservice.domain.model.response;

import buyukkaya.userservice.domain.model.dto.EvDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class GetEvResponse extends BaseResponse {

    private List<EvDto> evList;

    private EvDto ev;

}
