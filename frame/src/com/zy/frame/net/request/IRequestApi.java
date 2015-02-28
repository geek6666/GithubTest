package com.zy.frame.net.request;

import android.content.Context;
import com.loopj.android.http.RequestParams;
import com.zy.frame.net.cache.CacheEntity;
import com.zy.frame.net.listener.RequestCompleteListener;
import com.zy.frame.net.parse.base.IParseApi;

/**
 * Created by Administrator on 2015/2/11
 * 网络请求的接口
 */
public interface IRequestApi {
    /**
     * 网络请求
     *
     * @param context
     * @param url           请求url
     * @param requestParams 请求参数
     * @param parseUtils    自定义解析器
     * @param cacheType     缓存类型
     * @param listener      请求接口的监听
     */
    public void post(Context context, String url, RequestParams requestParams, IParseApi parseUtils,CacheEntity cacheEntity, RequestCompleteListener listener);


}
