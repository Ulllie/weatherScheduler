package org.ulllie.weatherscheduler.scheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.ulllie.weatherscheduler.scheduler.interceptors.OpenWeatherInterceptorKey;

@Configuration
public class OpenWeatherConfig {

    @Bean
    public RestClient openWeatherClient(OpenWeatherInterceptorKey openWeatherInterceptorKey) {
        return RestClient.builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/weather")
                .requestInterceptor(openWeatherInterceptorKey)
                .build();
    }

}
