package buyukkaya.userservice.domain.model.request;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRegistrationRequest {

    //TODO ADD MOBILE PHONE NUMBER
    private UUID id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

}
