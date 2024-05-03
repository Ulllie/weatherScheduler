package org.ulllie.weather.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ulllie.weather.table.Weather;

import java.util.UUID;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, UUID> {
}