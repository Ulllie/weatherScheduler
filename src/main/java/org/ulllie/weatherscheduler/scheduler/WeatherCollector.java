package org.ulllie.weatherscheduler.scheduler;

import lombok.AllArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.ulllie.weatherscheduler.dto.WeatherMainInfo;
import org.ulllie.weatherscheduler.repository.WeatherRepository;
import org.ulllie.weatherscheduler.table.Weather;
import org.ulllie.weatherscheduler.weather_client.WeatherClient;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class WeatherCollector {

    @Autowired
    WeatherClient weatherClient;

    @Autowired
    WeatherRepository weatherRepository;

    private static final Logger logger = LoggerFactory.getLogger(WeatherCollector.class);


    @Scheduled(cron = "0/10 * * * * ?")
    @SchedulerLock(name = "collect_weather_task")
    public void collect() {
        String city = "Moscow";
        logger.info("Start collect weather in {}", city);
        WeatherMainInfo weatherInfo = weatherClient.get(city);

        Weather weatherEntity = new Weather(
                weatherInfo.temp(),
                weatherInfo.feelsLike(),
                OffsetDateTime.now(),
                city
        );

        weatherRepository.save(weatherEntity);
    }

}
