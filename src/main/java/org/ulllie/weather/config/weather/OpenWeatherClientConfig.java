package org.ulllie.weather.config.weather;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.ulllie.weather.interceptors.OpenWeatherInterceptorKey;

@Configuration
public class OpenWeatherClientConfig {

    @Bean
    public RestClient openWeatherClient(OpenWeatherInterceptorKey openWeatherInterceptorKey) {
        return RestClient.builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/weather")
                .requestInterceptor(openWeatherInterceptorKey)
                .build();
    }

}
