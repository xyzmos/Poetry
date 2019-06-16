package com.nextrt.poetry.mapper;

import com.nextrt.poetry.entity.Poetry;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
@CacheConfig(cacheNames = "poetry")
public interface PoetryMapper {

    @Cacheable(value = "poetry",unless = "#result == null ")
    @Select("select * from poetry")
    List<Poetry> selectAll();

    @Cacheable(value = "poetry",key = "'poetryid_'+#poetryId",unless = "#result == null ")
    @Select("select * from poetry where poetryId = #{poetryId}")
    Poetry selectByPoetryId(int poetryId);

    //获取上一首展示的诗用访问者的ip
    @CachePut(value = "latestpoetry", key = "'_'+#ip", unless = "#result == null ")
    @Select("select * from poetry where poetryId = #{poetryId}")
    Poetry lateatPoetry(int poetryId, String ip);
}