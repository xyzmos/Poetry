package com.nextrt.poetry.service.Impl;

import com.nextrt.poetry.entity.Tag;
import com.nextrt.poetry.mapper.TagMapper;
import com.nextrt.poetry.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;
    //添加省
    public void addCountry(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/home/xyzm/Project/poetry/src/main/resources/data/sheng")));
            int i = 1;
            String str = "";
            while((str = bufferedReader.readLine())!=null){
                String temp[] = str.split("-");
                Tag tag = new Tag(temp[0],2,Integer.parseInt(temp[1]));
                tagMapper.insertTag(tag);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //
    //添加市
    /*
    * 说明
    * 省市文件内容格式参考data/江苏 江苏省文件
    * 金陵、江宁、建业、建康、升州、天京、南京、玄武湖、栖霞山->南京
    *
    * */
    public void addCity(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("F:\\ideaProjects\\poetry\\src\\main\\resources\\data\\省\\西南\\重庆")));
            String str = "";
            while((str = bufferedReader.readLine())!=null){
                String temp[] = str.split("->");
                Tag tag = new Tag(temp[1],3,28);
                tagMapper.insertTag(tag);
                String places[] = temp[0].split("、");
                for (String place : places) {
                    Tag placeTag = new Tag(place,4,tag.getTagId());
                    tagMapper.insertTag(placeTag);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addWeather() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/home/xyzm/Project/poetry/src/main/resources/data/天气.md")));
            String str = "";
            while((str = bufferedReader.readLine())!=null){
                String temp[] = str.split("->");
                Tag tag = new Tag(temp[1],5,0);
                tagMapper.insertTag(tag);
                String tags[] = temp[0].split("、");
                for (String tagx : tags) {
                    Tag placeTag = new Tag(tagx,6,tag.getTagId());
                    tagMapper.insertTag(placeTag);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTime() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/home/xyzm/Project/poetry/src/main/resources/data/时间")));
            String str = "";
            while((str = bufferedReader.readLine())!=null){
                String temp[] = str.split("->");
                Tag tag = new Tag(temp[1],7,0);
                tagMapper.insertTag(tag);
                String tags[] = temp[0].split("、");
                for (String tagx : tags) {
                    Tag placeTag = new Tag(tagx,8,tag.getTagId());
                    tagMapper.insertTag(placeTag);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSeason() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/home/xyzm/Project/poetry/src/main/resources/data/季节/春夏秋冬")));
            String str = "";
            while((str = bufferedReader.readLine())!=null){
                String temp[] = str.split("->");
                Tag tag = new Tag(temp[1],9,0);
                tagMapper.insertTag(tag);
                String tags[] = temp[0].split("、");
                for (String tagx : tags) {
                    Tag placeTag = new Tag(tagx,10,tag.getTagId());
                    tagMapper.insertTag(placeTag);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
