package org.ulllie.weather.scheduler;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.ulllie.weather.dto.WeatherMainInfo;
import org.ulllie.weather.repository.WeatherRepository;
import org.ulllie.weather.table.Weather;
import org.ulllie.weather.weather_client.WeatherService;

import java.time.OffsetDateTime;

@Component
public class WeatherCollector {

    private final WeatherService weatherService;
    private final WeatherRepository weatherRepository;

    private static final Logger logger = LoggerFactory.getLogger(WeatherCollector.class);

    public WeatherCollector(WeatherService weatherService, WeatherRepository weatherRepository) {
        this.weatherService = weatherService;
        this.weatherRepository = weatherRepository;
    }

    @Scheduled(cron = "0/10 * * * * ?")
    @SchedulerLock(name = "collect_weather_task")
    public void collect() {
        String city = "Moscow";
        logger.info("Start collect weather in {}", city);
        WeatherMainInfo weatherInfo = weatherService.getMainInfo(city);

        Weather weatherEntity = new Weather(
                weatherInfo.temp(),
                weatherInfo.feelsLike(),
                OffsetDateTime.now(),
                city
        );

        weatherRepository.save(weatherEntity);
    }
}
