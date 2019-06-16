package com.nextrt.poetry.service.Impl;

import com.nextrt.poetry.entity.Poetry;
import com.nextrt.poetry.entity.PoetryList;
import com.nextrt.poetry.entity.Tag;
import com.nextrt.poetry.mapper.PoetryListMapper;
import com.nextrt.poetry.mapper.PoetryMapper;
import com.nextrt.poetry.mapper.TagMapper;
import com.nextrt.poetry.service.PoetryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PoetryListServiceImpl implements PoetryListService {
    @Autowired
    PoetryMapper poetryMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    PoetryListMapper poetryListMapper;

    @Override
    public void forEachPoetry() {
        List<Poetry> poetryList = poetryMapper.selectAll();
        List<Tag> oldCityTags = tagMapper.selectByTagType(4);//古时城市旧称列表
        List<Tag> weatherTags = tagMapper.selectByTagType(6);//天气类型关键词
        List<Tag> timeTags = tagMapper.selectByTagType(8);//时间的关键词
        List<Tag> seasonTags = tagMapper.selectByTagType(10);//季节的关键词
        for (Poetry poetry:poetryList) {
            //城市标签
            List<Integer> cityId = new ArrayList<Integer>();//城市ID
            List<Integer> provinceId = new ArrayList<Integer>();//省份ID
            List<Integer> areaId = new ArrayList<Integer>();//区域ID
            for (Tag tag:oldCityTags) {//古时候城市的标签
                if(poetry.getContent().contains(tag.getTagName()) || poetry.getSubject().contains(tag.getTagName())){
                    cityId.add(tag.getTagSuperId());//添加城市标签
                    Tag cityTagTemp = tagMapper.selectByTagId(tag.getTagSuperId());//获取城市标签详情
                    provinceId.add(cityTagTemp.getTagSuperId());
                    Tag tempTag = tagMapper.selectByTagId(cityTagTemp.getTagSuperId());
                    areaId.add(tempTag.getTagSuperId());
                }
            }
            //天气标签
            List<Integer> weatherId = new ArrayList<Integer>();
            for (Tag tag:weatherTags) {
                if(poetry.getContent().contains(tag.getTagName()) || poetry.getSubject().contains(tag.getTagName())){
                    if(!weatherId.contains(tag.getTagSuperId())){
                        weatherId.add(tag.getTagSuperId());
                    }
                }
            }

            List<Integer> timeId = new ArrayList<Integer>();
            for (Tag tag:timeTags) {
                if(poetry.getContent().contains(tag.getTagName()) || poetry.getSubject().contains(tag.getTagName())){
                    if(!timeId.contains(tag.getTagSuperId())){
                        timeId.add(tag.getTagSuperId());
                    }
                }
            }

            List<Integer> seasonId = new ArrayList<Integer>();
            for (Tag tag:seasonTags) {
                if(poetry.getContent().contains(tag.getTagName()) || poetry.getSubject().contains(tag.getTagName())){
                    if(!seasonId.contains(tag.getTagSuperId())){
                        seasonId.add(tag.getTagSuperId());
                    }
                }
            }
            for(int a=0;a<=areaId.size();a++){
                PoetryList poetryList1 = new PoetryList();
                poetryList1.setPoetryId(poetry.getPoetryId());
                Integer temp = areaId.size()>a?areaId.get(a):null;
                poetryList1.setAreaId(temp);
                poetryList1.setProvinceId(provinceId.size()>a?provinceId.get(a):null);
                poetryList1.setCityId(cityId.size()>a?cityId.get(a):null);
                for (int d=0;d<=timeId.size();d++){
                    temp = timeId.size()>d?timeId.get(d):null;
                    poetryList1.setTimeId(temp);
                    for (int e=0;e<=seasonId.size();e++){
                        temp = seasonId.size()>e?seasonId.get(e):null;
                        poetryList1.setSeasonId(temp);
                        for (int f=0;f<=weatherId.size();f++){
                            temp = weatherId.size()>f?weatherId.get(f):null;
                            poetryList1.setWeatherId(temp);
                            addPoetryList(poetryList1);
                        }
                    }
                }
            }

        }

    }

    @Override
    public synchronized int addPoetryList(PoetryList poetryList) {
        if(poetryList.getAreaId()!=null && poetryList.getProvinceId()!=null && poetryList.getCityId()!=null && poetryList.getSeasonId()!=null && poetryList.getTimeId()!=null && poetryList.getWeatherId()!=null){
            return 0;
        }
        poetryListMapper.addPoetryList(poetryList);
        return 0;
    }
}
