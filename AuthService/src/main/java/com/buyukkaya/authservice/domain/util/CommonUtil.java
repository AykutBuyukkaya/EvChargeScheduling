package com.buyukkaya.authservice.domain.util;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

    public String parseExternalErrorMessage(Exception e) {
        final String exMessage = e.getMessage().substring(e.getMessage().indexOf("{"));
        return new JSONObject(exMessage).getString("errorMessage");
    }

}
