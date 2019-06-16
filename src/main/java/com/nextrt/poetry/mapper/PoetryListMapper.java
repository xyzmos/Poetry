package com.nextrt.poetry.mapper;

import com.nextrt.poetry.entity.PoetryList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


@CacheConfig(cacheNames = "PoetryList")
public interface PoetryListMapper {

    @CacheEvict(value = "PoetryList",allEntries = true)
    @Insert("insert into poetryList (poetryId,areaId,provinceId,cityId,seasonId,timeId,weatherId) values (#{poetryId},#{areaId},#{provinceId},#{cityId},#{seasonId},#{timeId},#{weatherId})")
    int addPoetryList(PoetryList poetryList);


    @Cacheable(value = "PoetryList",key = "'CTId_'+#poetryList",unless = "#result==null")
    int CountByTagId(PoetryList poetryList);


    @Cacheable(value = "PoetryList",key = "'sBCN_'+#poetryList",unless = "#result==null")
    int selectByCountNum(PoetryList poetryList);

}