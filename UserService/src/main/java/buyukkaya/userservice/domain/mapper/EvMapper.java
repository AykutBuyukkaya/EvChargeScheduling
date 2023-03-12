package buyukkaya.userservice.domain.mapper;

import buyukkaya.userservice.domain.model.dto.EvDto;
import buyukkaya.userservice.domain.model.entity.Ev;
import buyukkaya.userservice.domain.model.request.AddEvRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EvMapper {

    Ev toEv(AddEvRequest request);

    EvDto toEvDto(Ev ev);

    List<EvDto> toEvDtoList(List<Ev> evList);

}
