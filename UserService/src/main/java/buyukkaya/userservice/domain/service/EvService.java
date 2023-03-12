package buyukkaya.userservice.domain.service;

import buyukkaya.userservice.domain.model.dto.EvDto;
import buyukkaya.userservice.domain.model.request.AddEvRequest;

import java.util.List;
import java.util.UUID;

public interface EvService {

    EvDto addEv(AddEvRequest addEvRequest);

    List<EvDto> getUserEVs(UUID userId);

}
