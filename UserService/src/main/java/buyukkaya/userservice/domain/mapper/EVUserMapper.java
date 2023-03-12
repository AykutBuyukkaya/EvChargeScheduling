package buyukkaya.userservice.domain.mapper;

import buyukkaya.userservice.domain.model.entity.EVUser;
import buyukkaya.userservice.domain.model.request.UserRegistrationRequest;
import org.mapstruct.Mapper;

@Mapper
public interface EVUserMapper {

    EVUser toEVUser(UserRegistrationRequest userRegistrationRequest);

}
