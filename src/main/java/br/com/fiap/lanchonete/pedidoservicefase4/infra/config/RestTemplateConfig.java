package br.com.fiap.lanchonete.pedidoservicefase4.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        restTemplate.getMessageConverters().add(0, converter);  // Add the converter to the beginning

        restTemplate.getInterceptors().add(new LogginInterceptor());
   
        return restTemplate;



        //return new RestTemplate();
    }
}
