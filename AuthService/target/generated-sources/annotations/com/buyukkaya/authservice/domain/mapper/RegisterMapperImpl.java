package com.buyukkaya.authservice.domain.mapper;

import com.buyukkaya.authservice.domain.model.request.Credential;
import com.buyukkaya.authservice.domain.model.request.KeycloakRegisterRequest;
import com.buyukkaya.authservice.domain.model.request.RegisterRequest;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-24T22:04:02+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class RegisterMapperImpl implements RegisterMapper {

    @Override
    public KeycloakRegisterRequest mapToKeycloak(RegisterRequest request) {
        if ( request == null ) {
            return null;
        }

        KeycloakRegisterRequest.KeycloakRegisterRequestBuilder<?, ?> keycloakRegisterRequest = KeycloakRegisterRequest.builder();

        keycloakRegisterRequest.username( request.getUsername() );
        keycloakRegisterRequest.email( request.getEmail() );
        keycloakRegisterRequest.firstName( request.getFirstName() );
        keycloakRegisterRequest.lastName( request.getLastName() );

        keycloakRegisterRequest.credentials( List.of(new Credential("password",request.getPassword(),false)) );
        keycloakRegisterRequest.enabled( true );

        return keycloakRegisterRequest.build();
    }
}
