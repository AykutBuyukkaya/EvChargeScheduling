package buyukkaya.userservice.domain.repository;

import buyukkaya.userservice.domain.model.entity.Ev;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EvRepository extends JpaRepository<Ev, UUID> {

    List<Ev> findAllByEvUser_Id(UUID userId);

}
