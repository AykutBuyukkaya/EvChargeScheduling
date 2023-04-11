package com.buyukkaya.chargingstationservice.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoDBConfig {

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory, MappingMongoConverter mappingMongoConverter) {
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        mappingMongoConverter.setCustomConversions(customConversions());
        return new MongoTemplate(mongoDatabaseFactory, mappingMongoConverter);
    }

    @Bean
    public MongoCustomConversions customConversions(){
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(new MongoTimeWritingConverter());
        converterList.add(new MongoTimeReadingConverter());
        return new MongoCustomConversions(converterList);
    }




}
