package com.nextrt.poetry.service;

import com.nextrt.poetry.entity.WeatherInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;



@CacheConfig(cacheNames = "DistrictWeather")
public interface DistrictWeatherService {

	@Cacheable(value = "DistrictWeather", key = "'PoetryWeather_'+#ip", unless = "#result == null ",cacheManager = "cacheManagerHour")
	WeatherInfo getIpAndWeather(String ip);
}
