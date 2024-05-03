package org.ulllie.weatherscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
public class WeatherSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherSchedulerApplication.class, args);
	}

}
