package org.ulllie.weather.weather_client.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.ulllie.weather.dto.WeatherMainInfo;
import org.ulllie.weather.support.ResponseConverter;
import org.ulllie.weather.weather_client.WeatherClient;

@Component
public class WeatherClientImpl implements WeatherClient {

    private final RestClient openWeatherClient;

    private final ResponseConverter responseConverter;

    public WeatherClientImpl(
            RestClient openWeatherClient,
            ResponseConverter responseConverter
    ) {
        this.openWeatherClient = openWeatherClient;
        this.responseConverter = responseConverter;
    }

    @Override
    public WeatherMainInfo getMainInfo(String city) {
        String response = openWeatherClient.get()
                .uri(uri -> uri
                        .path("/")
                        .queryParam("q", city)
                        .queryParam("lang", "ru")
                        .build()
                )
                .retrieve()
                .body(String.class);

        return responseConverter.toDto(response, "main", WeatherMainInfo.class);
    }

}
