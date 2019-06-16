package com.nextrt.poetry.service;

import com.nextrt.poetry.entity.PoetryList;

public interface PoetryListService {
   void forEachPoetry();
   int addPoetryList(PoetryList poetryList);
}
