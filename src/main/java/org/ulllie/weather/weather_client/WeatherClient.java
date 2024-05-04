package org.ulllie.weather.weather_client;

import org.ulllie.weather.dto.WeatherMainInfo;

public interface WeatherClient {
    WeatherMainInfo getMainInfo(String city);
}
