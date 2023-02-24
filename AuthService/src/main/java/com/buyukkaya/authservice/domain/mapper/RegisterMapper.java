package com.buyukkaya.authservice.domain.mapper;

import com.buyukkaya.authservice.domain.model.request.Credential;
import com.buyukkaya.authservice.domain.model.request.KeycloakRegisterRequest;
import com.buyukkaya.authservice.domain.model.request.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(imports = {Credential.class, List.class})
public interface RegisterMapper {

    @Mapping(target = "credentials", expression = "java(List.of(new Credential(\"password\",request.getPassword(),false)))")
    @Mapping(target = "enabled", constant = "true")
    KeycloakRegisterRequest mapToKeycloak(RegisterRequest request);

}
