package com.zy.frame.net.cache;

import android.text.TextUtils;

/**
 * Created by zy on 2015/2/12.
 */
public class CacheEntity {
    public CacheType cacheType;//数据的缓存方式
    public String tableName;//数据库表的名字
    public Dao cacheDao;//dao操作


    public CacheEntity(CacheType cacheType, String tableName) {
        this.cacheType = cacheType;
        this.tableName = tableName;
    }

    public CacheEntity(CacheType cacheType, String tableName, Dao dao) {
        this.cacheType = cacheType;
        this.tableName = tableName;
        this.cacheDao = dao;
    }


    /**
     * 判断当前是否需要缓存数据
     *
     * @return true 需要缓存
     */
    public boolean shouldCache() {
        boolean shouldCache = false;
        switch (cacheType) {
            case TYPE_NO_CACHE:
                shouldCache = false;
                break;
            case TYPE_USE_CACHE:
            case TYPE_CACHE_REFRESH:
                shouldCache = !TextUtils.isEmpty(tableName);//当数据库的表名不为空，并且当前不是刷新时，使用缓存
                break;
        }
        return shouldCache;
    }

    /**
     * 判断当前是否需要使用缓存
     *
     * @return true 使用缓存
     */
    public boolean useCache() {
        boolean useCache = false;
        switch (cacheType) {
            case TYPE_NO_CACHE:
            case TYPE_CACHE_REFRESH://当缓存类型是不缓存或者是刷新时，是不使用缓存的
                useCache = false;
                break;
            case TYPE_USE_CACHE://当缓存类型是使用缓存，则是需要是使用缓存，这事需要判断当前表名是否为null
                useCache = !TextUtils.isEmpty(tableName);
                break;
        }
        return useCache;
    }
}
