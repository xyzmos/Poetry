package com.nextrt.poetry.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoetryList implements Serializable {
    private Integer poetryTagId;

    private Integer poetryId;

    private Integer areaId;

    private Integer provinceId;

    private Integer cityId;

    private Integer seasonId;

    private Integer timeId;

    private Integer weatherId;

    private static final long serialVersionUID = 1L;

    public PoetryList(Integer poetryId, Integer areaId, Integer provinceId, Integer cityId, Integer seasonId, Integer timeId, Integer weatherId) {
        this.poetryId = poetryId;
        this.areaId = areaId;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.seasonId = seasonId;
        this.timeId = timeId;
        this.weatherId = weatherId;
    }

    @Override
    public String toString() {
        return "PL_" +
                "pTId=" + poetryTagId +
                ", pd=" + poetryId +
                ", ad=" + areaId +
                ", pvd=" + provinceId +
                ", cd=" + cityId +
                ", sd=" + seasonId +
                ", td=" + timeId +
                ", wd=" + weatherId +
                '}';
    }

}