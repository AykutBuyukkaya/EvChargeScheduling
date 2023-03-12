package com.buyukkaya.authservice.domain.mapper;

import com.buyukkaya.authservice.domain.model.request.Credential;
import com.buyukkaya.authservice.domain.model.request.KeycloakRegisterRequest;
import com.buyukkaya.authservice.domain.model.request.RegisterRequest;
import com.buyukkaya.authservice.domain.model.response.RegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.util.List;

@Mapper(imports = {Credential.class, List.class, Instant.class})
public interface RegisterMapper {

    @Mapping(target = "credentials", expression = "java(List.of(new Credential(\"password\",request.getPassword(),false)))")
    @Mapping(target = "enabled", constant = "true")
    KeycloakRegisterRequest mapToKeycloak(RegisterRequest request);


    @Mapping(target = "timestamp", expression = "java(Instant.now())")
    RegisterResponse toRegisterResponse(RegisterRequest request);

}
