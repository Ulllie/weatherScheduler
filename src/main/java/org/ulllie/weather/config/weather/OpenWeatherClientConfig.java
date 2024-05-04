package org.ulllie.weather.config.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.ulllie.weather.interceptors.OpenWeatherInterceptorKey;

@Configuration
public class OpenWeatherClientConfig {

    @Value("${open-weather.url}")
    private String OPEN_WEATHER_ROOT_URL;

    @Bean
    public RestClient openWeatherClient(OpenWeatherInterceptorKey openWeatherInterceptorKey) {
        return RestClient.builder()
                .baseUrl(OPEN_WEATHER_ROOT_URL + "/weather")
                .requestInterceptor(openWeatherInterceptorKey)
                .build();
    }
}
