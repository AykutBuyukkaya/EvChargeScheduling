package com.buyukkaya.authservice.domain.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class KeycloakRegisterRequest extends BaseRegisterRequest {

    private List<Credential> credentials;

}
