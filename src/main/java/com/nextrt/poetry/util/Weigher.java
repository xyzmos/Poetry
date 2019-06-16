package com.nextrt.poetry.util;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Component;

@Component
public class Weigher {
    public <T> T getWeighedInstance(Map<T, Integer> weighMap) {
        int totalWeight = 0;
        for (Map.Entry<T, Integer> entry : weighMap.entrySet()) {
            totalWeight += entry.getValue();
        }
        int num = ThreadLocalRandom.current().nextInt(totalWeight);
        for (Map.Entry<T, Integer> entry : weighMap.entrySet()) {
            num -= entry.getValue(); if(num<0){
                return entry.getKey();
            }
        }
        return null;
    }
}

