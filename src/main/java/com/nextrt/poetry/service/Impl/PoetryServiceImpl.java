package com.nextrt.poetry.service.Impl;

import com.google.common.collect.Maps;
import com.nextrt.poetry.entity.Poetry;
import com.nextrt.poetry.entity.PoetryList;
import com.nextrt.poetry.entity.Tag;
import com.nextrt.poetry.entity.WeatherInfo;
import com.nextrt.poetry.mapper.PoetryListMapper;
import com.nextrt.poetry.mapper.PoetryMapper;
import com.nextrt.poetry.mapper.TagMapper;
import com.nextrt.poetry.service.DistrictWeatherService;
import com.nextrt.poetry.service.PoetryService;
import com.nextrt.poetry.util.Weigher;
import com.nextrt.poetry.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PoetryServiceImpl implements PoetryService {
	private final PoetryMapper poetryMapper;
	private final TagMapper tagMapper;
	private final PoetryListMapper poetryListMapper;
	private final Weigher weigher;
	private final List<Tag> tagList ;

	@Autowired
	public PoetryServiceImpl(PoetryMapper poetryMapper, TagMapper tagMapper, PoetryListMapper poetryListMapper, DistrictWeatherService districtWeatherService, Weigher weigher) {
		this.poetryMapper = poetryMapper;
		this.tagMapper = tagMapper;
		this.poetryListMapper = poetryListMapper;
		tagList = tagMapper.selectAll();
		this.weigher = weigher;
	}

	@Override
	public Result getPoetry(WeatherInfo weatherInfo){
		//获取区域信息
		String area = tagMapper.selectByTagId(tagMapper.selectByTagName(weatherInfo.getProvince(),2).getTagSuperId()).getTagName();
		String season = getNowSeason();
		String time = getNowTime();

		//封装成一条数据
		String content = weatherInfo.getCity()+","+weatherInfo.getProvince()+","+area+","+season+","+time+","+weatherInfo.getWeather();
		Map<String,String> tagmap = new HashMap<String,String>(){{
			put("area",area);
			put("province",weatherInfo.getProvince());
			put("city",weatherInfo.getCity());
			put("season",season);
			put("time",time);
			put("weather",weatherInfo.getWeather());
		}};
		PoetryList poetryList = new PoetryList();
		for (Tag tag:tagList) {
			if (content.contains(tag.getTagName())) {
				switch (tag.getTagType()) {
					case 1:
						poetryList.setAreaId(tag.getTagId());
						break;
					case 2:
						poetryList.setProvinceId(tag.getTagId());
						break;
					case 3:
						poetryList.setCityId(tag.getTagId());
						break;
					case 5:
						poetryList.setWeatherId(tag.getTagId());
						break;
					case 7:
						poetryList.setTimeId(tag.getTagId());
						break;
					case 9:
						poetryList.setSeasonId(tag.getTagId());
						break;
				}
			}
		}
		poetryList = randomTag(poetryList);
		//查询符合该条件的记录条数
		int count = poetryListMapper.CountByTagId(poetryList);
		//当该条件不符合时重新生成条件
		if(count == 0) return getPoetry(weatherInfo);
		//生成随机数用于随机取出符合条件的古诗词
		poetryList.setPoetryTagId((int)(0+Math.random()*(count-1)));
		Poetry poetry = poetryMapper.selectByPoetryId(poetryListMapper.selectByCountNum(poetryList));

		Map<String,Object> poetryResult = new HashMap<>();
		poetryResult.put("poetry",poetry);
		poetryResult.put("tag",resultTag(poetryList,tagmap));

		return new Result(1,"查询成功",poetryResult);

	}
	private Map<String,String> resultTag(PoetryList poetryList,Map<String,String> tagmap){
		if(poetryList.getAreaId()==null)
			tagmap.remove("area");
		if(poetryList.getProvinceId()==null)
			tagmap.remove("province");
		if(poetryList.getCityId()==null)
			tagmap.remove("city");
		if(poetryList.getSeasonId()==null)
			tagmap.remove("season");
		if(poetryList.getTimeId()==null)
			tagmap.remove("time");
		if(poetryList.getWeatherId()==null)
			tagmap.remove("weather");
		return tagmap;
	}

	private PoetryList randomTag(PoetryList poetryList){
		PoetryList random = new PoetryList();
		//依据权重对省市区进行随机
		Map<Integer, Integer> weight = Maps.newLinkedHashMap();
		weight.put(1,25);
		weight.put(2,25);
		weight.put(3,25);
		weight.put(4,10);
		switch (weigher.getWeighedInstance(weight)){
			case 1:
				random.setAreaId(poetryList.getAreaId());
				break;
			case 2:
				random.setProvinceId(poetryList.getProvinceId());
				break;
			case 3:
				random.setCityId(poetryList.getCityId());
				break;
			default:
				break;
		}
		random.setSeasonId(randomSet(poetryList.getSeasonId(),20));
		random.setTimeId(randomSet(poetryList.getTimeId(),16));
		random.setWeatherId(randomSet(poetryList.getWeatherId(),14));
		return random;
	}

	private Integer randomSet(Integer id,int rate){
		Map<Integer, Integer> weight = Maps.newLinkedHashMap();
		weight.put(1,rate);
		weight.put(2,10);
		if(weigher.getWeighedInstance(weight)!=1){
			return null;
		}else{
			return  id;
		}
	}

	//获取当前月份获得季节
	private String getNowSeason(){
		Calendar cal= Calendar.getInstance();
		int month=cal.get(Calendar.MONTH)+1;
		if (month<4)
			return "春";
		else if(month<7)
			return "夏";
		else if(month<10)
			return "秋";
		else
			return "冬";
	}

	//获取当前时间，获得时间段
	private String getNowTime(){
		Calendar cal= Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if(hour>=4 && hour<10)
			return "清晨";
		else if(hour>=10 && hour<16)
			return "正午";
		else if(hour>=16 && hour<20)
			return "傍晚";
		else
			return "深夜";
	}



}

