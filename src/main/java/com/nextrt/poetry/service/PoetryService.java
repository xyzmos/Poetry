package com.nextrt.poetry.service;

import com.nextrt.poetry.entity.WeatherInfo;
import com.nextrt.poetry.vo.Result;


public interface PoetryService {

	Result getPoetry(WeatherInfo weatherInfo);

}


