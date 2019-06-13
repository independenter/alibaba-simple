package com.alibaba.matcher;

import java.util.List;

public interface Matcher {
    double getProportion(ObjectData obj1,ObjectData obj2,InfluencingFactor... factors) throws NoSuchFieldException, IllegalAccessException;
    List<Double> getProportion(ObjectData obj,List<ObjectData> objs, InfluencingFactor... factors);
}
