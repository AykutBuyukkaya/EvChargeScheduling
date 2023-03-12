package buyukkaya.userservice.domain.repository;

import buyukkaya.userservice.domain.model.entity.EVUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EvUserRepository extends JpaRepository<EVUser, String> {

    Optional<EVUser> findById(UUID id);

}
