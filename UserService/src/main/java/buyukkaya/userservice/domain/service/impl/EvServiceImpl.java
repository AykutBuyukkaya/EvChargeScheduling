package buyukkaya.userservice.domain.service.impl;

import buyukkaya.userservice.domain.exception.EvServiceException;
import buyukkaya.userservice.domain.mapper.EvMapper;
import buyukkaya.userservice.domain.model.dto.EvDto;
import buyukkaya.userservice.domain.model.entity.EVUser;
import buyukkaya.userservice.domain.model.entity.Ev;
import buyukkaya.userservice.domain.model.request.AddEvRequest;
import buyukkaya.userservice.domain.repository.EvRepository;
import buyukkaya.userservice.domain.repository.EvUserRepository;
import buyukkaya.userservice.domain.service.EvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class EvServiceImpl implements EvService {

    private final EvRepository evRepository;

    private final EvUserRepository evUserRepository;
    private final EvMapper evMapper;

    public EvServiceImpl(EvRepository evRepository, EvUserRepository evUserRepository, EvMapper evMapper) {
        this.evRepository = evRepository;
        this.evUserRepository = evUserRepository;
        this.evMapper = evMapper;
    }

    @Override
    public EvDto addEv(AddEvRequest addEvRequest) {

        EVUser evUser = evUserRepository.findById(addEvRequest.getUserId())
                .orElseThrow(() -> new EvServiceException("User is not found!"));

        Ev ev = evMapper.toEv(addEvRequest);
        ev.setEvUser(evUser);
        return evMapper.toEvDto(evRepository.save(ev));

    }

    @Override
    public List<EvDto> getUserEVs(UUID userID) {

        return evMapper.toEvDtoList(evRepository.findAllByEvUser_Id(userID));

    }
}
