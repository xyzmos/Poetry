package com.nextrt.poetry.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nextrt.poetry.entity.WeatherInfo;
import com.nextrt.poetry.service.DistrictWeatherService;
import com.nextrt.poetry.util.OKHttpUtil;
import com.nextrt.poetry.util.Tool;
import org.springframework.stereotype.Service;


@Service
public class DistrictWeatherServiceImpl implements DistrictWeatherService {
	private final OKHttpUtil okHttpUtil;

	private final Tool tool;

	public DistrictWeatherServiceImpl(OKHttpUtil okHttpUtil, Tool tool) {
		this.okHttpUtil = okHttpUtil;
		this.tool = tool;
	}

	@Override
	public WeatherInfo getIpAndWeather(String ip) {
		String res = okHttpUtil.get("https://zpi.nextrt.com/tool//weather/api/2/"+ip,null);
		WeatherInfo weatherInfo = null;
		if(res!=null && !res.equals("")){
			JSONObject jsonObject = JSON.parseObject(res);
			if (jsonObject.getInteger("status") == 1) {
				weatherInfo = new WeatherInfo();
				JSONObject data = JSON.parseObject(jsonObject.getString("data"));
				//解析IP信息获取省市
				JSONObject locationInfo = JSON.parseObject(data.getString("ip"));
				String city = locationInfo.getString("city");
				city = city.replace("市", "");
				String province = locationInfo.getString("province").replaceAll("[省,市]|(自治区)+","");
				weatherInfo.setCity(city);
				weatherInfo.setProvince(province);
				//天气解析部分
				String temp = data.getString("weather");
				temp = JSON.parseObject(temp).getString("casts");
				temp = ((JSONObject)JSON.parseArray(temp).get(0)).getString("dayweather");
				weatherInfo.setWeather(judgeWeather(temp));
				weatherInfo.setExpire(tool.getEndTime());
			}
		}
		return weatherInfo;
	}

	//判断天气状态
	private String judgeWeather(String weather){
		StringBuilder stringBuilder = new StringBuilder("");
		for(int i=0;i<weather.length();i++){
			String now = String.valueOf(weather.charAt(i));
			if(now.matches("[晴云雨雪雾雷风阴]{1}"))
				stringBuilder.append(now);
		}
		if(stringBuilder.length()>1){
			int num = (int)(0+Math.random()*stringBuilder.length());
			return String.valueOf(String.valueOf(stringBuilder).charAt(num)).replace("阴","云");
		}else if(stringBuilder.length()==1)
			return String.valueOf(stringBuilder).replace("阴","云");
		else{
			return "晴";
		}
	}

}
