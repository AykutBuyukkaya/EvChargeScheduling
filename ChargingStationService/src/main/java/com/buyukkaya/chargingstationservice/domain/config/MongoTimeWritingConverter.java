package com.buyukkaya.chargingstationservice.domain.config;

import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.time.LocalTime;
import java.util.Optional;

import static com.buyukkaya.chargingstationservice.domain.util.Constants.DATE_TIME_CONVERTER;


@NoArgsConstructor
@WritingConverter
public class MongoTimeWritingConverter implements Converter<LocalTime, String> {


    @Override
    public String convert(LocalTime source) {
        return Optional.ofNullable(source).map(DATE_TIME_CONVERTER::format).orElse("");
    }
}
