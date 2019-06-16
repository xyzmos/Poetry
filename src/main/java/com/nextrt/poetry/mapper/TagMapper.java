package com.nextrt.poetry.mapper;

import com.nextrt.poetry.entity.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
@CacheConfig(cacheNames = "tag")
public interface TagMapper {
    @Cacheable(value = "tag",key = "'selectByTagName_'+#TagName+'tagType_'+#tagType",unless = "#result == null")
    @Select("select * FROM tag where tagName = #{tagName} and tagType = #{tagType}")
    Tag selectByTagName(String tagName,int tagType);

    @CacheEvict(value = "tag",allEntries = true)
    @Insert("insert into tag (tagName,tagType,tagSuperId) values (#{tagName},#{tagType},#{tagSuperId})")
    @Options(useGeneratedKeys = true,keyColumn = "tagId",keyProperty = "tagId")
    int insertTag(Tag tag);

    @Cacheable(value = "tag",key = "'selectByTagType_'+#tagType",unless = "#result == null")
    @Select("select * from tag where tagType = #{tagType}")
    List<Tag> selectByTagType(int tagType);

    @Cacheable(value = "tag",key = "'selectByTagId_'+#tagId",unless = "#result == null")
    @Select("select * from tag where tagId = #{tagId}")
    Tag selectByTagId(int tagId);

    @Cacheable(value = "tag",key = "'selectByTagSuperId_'+ #tagSuperId",unless = "#result == null")
    @Select("select * from tag where tagSuperId = #{tagSuperId}")
    List<Tag> selectByTagSuperId(int tagSuperId);

    @Cacheable(value = "tag",key = "'TagAll'",unless = "#result == null")
    @Select("select * from tag")
    List<Tag> selectAll();

}