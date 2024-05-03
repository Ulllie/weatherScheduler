package org.ulllie.weather.weather_client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.ulllie.weather.dto.WeatherMainInfo;

@Component
public class WeatherService {

    private final RestClient openWeatherClient;
    private final ObjectMapper mapper;

    public WeatherService(
            @Qualifier(value = "openWeatherClient") RestClient openWeatherClient,
            ObjectMapper mapper
    ) {
        this.openWeatherClient = openWeatherClient;
        this.mapper = mapper;
    }

    public WeatherMainInfo getMainInfo(String city) {
        String response = openWeatherClient.get()
                .uri("/?q={city}", city)
                .retrieve()
                .body(String.class);

        return convertToDto(response, "main", WeatherMainInfo.class);
    }

    private <D> D convertToDto(String response, Class<D> dto) {
        try {
            JsonNode jsonNodeResponse = mapper.readTree(response);
            return mapper.treeToValue(jsonNodeResponse, dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private <D> D convertToDto(String response, String mappedKey, Class<D> dto) {
        try {
            JsonNode jsonNodeResponse = mapper.readTree(response);
            JsonNode weatherMainInfo = jsonNodeResponse.path(mappedKey);
            return mapper.treeToValue(weatherMainInfo, dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
