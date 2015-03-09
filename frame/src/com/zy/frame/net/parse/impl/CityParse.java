package com.zy.frame.net.parse.impl;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zy.frame.entity.City;
import com.zy.frame.net.cache.CacheEntity;
import com.zy.frame.net.parse.base.BaseParseApi;

import java.util.List;

/**
 * Created by Administrator on 2015/2/12.
 */
public class CityParse extends BaseParseApi {
    @Override
    public List<City> parse(String jsonContent, CacheEntity cacheEntity) {
        Gson gson = new Gson();
        return gson.fromJson(jsonContent, new TypeToken<List<City>>() {
        }.getType());
    }
}
