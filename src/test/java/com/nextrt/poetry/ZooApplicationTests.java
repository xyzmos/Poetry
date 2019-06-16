package com.nextrt.poetry;

import com.nextrt.poetry.entity.PoetryList;
import com.nextrt.poetry.service.PoetryListService;
//import com.nextrt.poetry.service.PoetryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZooApplicationTests {
    @Autowired
    PoetryListService poetryListService;

    @Autowired
  //  PoetryService poetryService;
    @Test
    public void contextLoads() {
//        tagService.//addCity();
//                //addCountry();
//                //addWeather();
//        //addTime();
//        addSeason();
    //    poetryService.getPoetry();

        poetryListService.forEachPoetry();
        return;
    }

}
