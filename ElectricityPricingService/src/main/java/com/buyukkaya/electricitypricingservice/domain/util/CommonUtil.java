package com.buyukkaya.electricitypricingservice.domain.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;


@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Getter
public class CommonUtil {

    @Value("${comed.url}")
    private String comedAPIUrl;
    private final String DATE_TIME_FORMAT = "yyyyMMddHHmm";
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

}
