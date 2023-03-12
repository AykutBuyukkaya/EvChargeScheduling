package com.buyukkaya.authservice.domain.util;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class CommonUtil {

    public String parseExternalErrorMessage(Exception e) {
        final String exMessage = e.getMessage().substring(e.getMessage().indexOf("{"));
        return new JSONObject(exMessage).getString("errorMessage");
    }

    public UUID getUUIDFromKeycloakResponse(ResponseEntity<?> keycloakResponse) {
        String path = Objects.requireNonNull(keycloakResponse.getHeaders().getLocation()).getPath();
        System.out.println(path.substring(path.lastIndexOf("/") + 1));
        return UUID.fromString(path.substring(path.lastIndexOf("/") + 1));
    }

}
