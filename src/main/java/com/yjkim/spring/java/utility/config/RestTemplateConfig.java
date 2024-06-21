package com.yjkim.spring.java.utility.config;

import com.yjkim.spring.java.utility.exception.PlatformRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Configuration
public class RestTemplateConfig
{

    @Bean
    public RestTemplate restTemplate()
    {
        try
        {
            RestTemplate template = new RestTemplate();
            template.getInterceptors().add(restTemplateLoggingInterceptor());
            return template;
        } catch (Exception ex)
        {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }

    @Bean
    public RestTemplateLoggingInterceptor restTemplateLoggingInterceptor()
    {
        return new RestTemplateLoggingInterceptor();
    }
}
