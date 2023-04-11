package com.buyukkaya.chargingstationservice.domain.config;

import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.LocalTime;
import java.util.Optional;

import static com.buyukkaya.chargingstationservice.domain.util.Constants.DATE_TIME_CONVERTER;

@NoArgsConstructor
@ReadingConverter
public class MongoTimeReadingConverter implements Converter<String, LocalTime> {


    @Override
    public LocalTime convert(String source) {
        return Optional.ofNullable(source).map(s -> LocalTime.from(DATE_TIME_CONVERTER.parse(source))).orElse(LocalTime.MIN);
    }
}
