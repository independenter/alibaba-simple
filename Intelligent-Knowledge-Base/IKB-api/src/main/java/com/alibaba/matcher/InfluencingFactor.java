package com.alibaba.matcher;

public interface InfluencingFactor {
    double factor = 0;
    double getFactor();
    double getProportion(ObjectData obj1,ObjectData obj2) throws NoSuchFieldException, IllegalAccessException;
}
