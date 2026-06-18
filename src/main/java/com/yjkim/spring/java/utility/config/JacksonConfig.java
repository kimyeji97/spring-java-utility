package com.techlabs.common.config;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.techlabs.platform.core.data.CommonConst;
import com.techlabs.platform.core.processor.converter.BooleanDeserializer;
import com.techlabs.platform.core.processor.converter.LocalDateConverter;
import com.techlabs.platform.core.processor.converter.LocalDateTimeConverter;
import com.techlabs.platform.core.processor.converter.LocalTimeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfig {
    
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        
        
        DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern(CommonConst.DT_FM_DATE_TIME);
        DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern(CommonConst.DT_FM_DATE);
        DateTimeFormatter localTimeFormatter = DateTimeFormatter.ofPattern(CommonConst.DT_FM_TIME);
        
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(localDateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeConverter());
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(localDateFormatter));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateConverter());
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(localTimeFormatter));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeConverter());


        objectMapper.registerModule(javaTimeModule);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        return objectMapper;
    }
    
    @Bean(name = "snakeObjectMapper")
    public ObjectMapper snakeObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        
        DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern(CommonConst.DT_FM_DATE_TIME);
        DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern(CommonConst.DT_FM_DATE);
        DateTimeFormatter localTimeFormatter = DateTimeFormatter.ofPattern(CommonConst.DT_FM_TIME);
        
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(localDateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeConverter());
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(localDateFormatter));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateConverter());
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(localTimeFormatter));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeConverter());


        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Boolean.class, new BooleanDeserializer());
        
        objectMapper.registerModule(javaTimeModule);
        objectMapper.registerModule(simpleModule);
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}
