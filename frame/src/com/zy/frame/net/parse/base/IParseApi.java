package com.zy.frame.net.parse.base;

import com.zy.frame.net.cache.CacheEntity;
import com.zy.frame.net.listener.RequestCompleteListener;

/**
 * Created by Administrator on 2015/2/11.
 * json解析器
 */
public interface IParseApi {
    /**
     * json解析器
     *
     * @param json      获取到json字符串
     * @param cacheEntity 缓存实体
     * @param listener  网络请求的监听
     */
    public void jsonParser(String json, CacheEntity cacheEntity, RequestCompleteListener listener);
}
