package com.nextrt.poetry.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherInfo {
    String city;
    String weather;
    String province;
    Date expire;
}
