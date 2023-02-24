package com.buyukkaya.authservice.domain.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseRegisterRequest {

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private boolean enabled;

}
