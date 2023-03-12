package com.buyukkaya.authservice.domain.model.response;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisterResponse extends BaseResponse implements Serializable {

    private UUID id;
    private String username;

    private String email;

    private String firstName;

    private String lastName;


}
