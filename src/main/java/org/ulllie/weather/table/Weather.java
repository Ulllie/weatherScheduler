package org.ulllie.weather.table;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Table(name = "weather_info")
public record Weather(
        @Id
        UUID id,

        Double temperature,

        Double feelsLike,

        OffsetDateTime currDate,

        String city
) {
    public Weather(Double temperature, Double feelsLike, OffsetDateTime currDate, String city) {
        this(null, temperature, feelsLike, currDate, city);
    }
}
