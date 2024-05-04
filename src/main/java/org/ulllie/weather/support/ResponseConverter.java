package org.ulllie.weather.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ResponseConverter {

    private final ObjectMapper mapper;

    public ResponseConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public <D> D toDto(String response, Class<D> dto) {
        try {
            JsonNode jsonNodeResponse = mapper.readTree(response);
            return mapper.treeToValue(jsonNodeResponse, dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <D> D toDto(String response, String mappedKey, Class<D> dto) {
        try {
            JsonNode jsonNodeResponse = mapper.readTree(response);
            JsonNode weatherMainInfo = jsonNodeResponse.path(mappedKey);
            return mapper.treeToValue(weatherMainInfo, dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
