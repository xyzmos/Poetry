package com.nextrt.poetry.controller;

import com.nextrt.poetry.entity.WeatherInfo;
import com.nextrt.poetry.service.DistrictWeatherService;
import com.nextrt.poetry.service.PoetryService;
import com.nextrt.poetry.util.Tool;
import com.nextrt.poetry.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PoetryController {

	private final PoetryService poetryService;

	private final DistrictWeatherService districtWeatherService;

	private final Tool tool;

	@Autowired
	public PoetryController(PoetryService poetryService, DistrictWeatherService districtWeatherService, Tool tool) {
		this.poetryService = poetryService;
		this.districtWeatherService = districtWeatherService;
		this.tool = tool;
	}

	@RequestMapping({"poetry/{ip:[0-9.]+}","poetry"})
	public Result getPotry(@PathVariable(required = false) String ip,HttpServletRequest request) {
		if(ip == null || ip == "")
			ip = tool.getClientIp(request);
        WeatherInfo weatherInfo = districtWeatherService.getIpAndWeather(ip);
        if(weatherInfo == null){
            return new Result(-1,"网络访问出错",null);
        }else {
            return poetryService.getPoetry(weatherInfo);
        }
	}
}
