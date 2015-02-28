package com.zy.frame.net.request;

import android.content.Context;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.zy.frame.net.cache.CacheEntity;
import com.zy.frame.net.cache.CacheType;
import com.zy.frame.net.listener.RequestCompleteListener;
import com.zy.frame.net.parse.base.IParseApi;
import com.zy.frame.net.response.Response;
import org.apache.http.Header;

import java.util.List;

/**
 * Created by Administrator on 2015/2/11.
 * 网络请求的工具
 */
public class RequestApi implements IRequestApi {
    /**
     * 默认的缓存类型
     */
    private static final CacheType DEFAULT_CACHE_TYPE = CacheType.TYPE_NO_CACHE;
    /**
     * 默认的缓存实体
     */
    private CacheEntity mDefaultCacheEntity;

    public RequestApi() {
        this.mDefaultCacheEntity = new CacheEntity(DEFAULT_CACHE_TYPE, null);
    }

    public void post(Context context, RequestParams requestParams, IParseApi parseUtils, RequestCompleteListener listener) {
        this.post(context, UrlAddress.BASIC_URL, requestParams, parseUtils, listener);
    }

    public void post(Context context, String url, RequestParams requestParams, IParseApi parseUtils, RequestCompleteListener listener) {
        this.post(context, url, requestParams, parseUtils, mDefaultCacheEntity, listener);
    }

    @Override
    public void post(Context context, String url, RequestParams requestParams, IParseApi parseUtils, CacheEntity cacheEntity, RequestCompleteListener listener) {
        //判断缓存是否需要缓存并且缓存没有过期
        if (cacheEntity != null && cacheEntity.dao != null && cacheEntity.useCache() && !cacheEntity.dao.isExpire()) {
            List<Object> dataList = cacheEntity.dao.query();//取出缓存数据，并返回
            if (dataList != null && dataList.size() > 0) {
                Response response = new Response();
                response.error = 0;
                response.content = dataList;
                listener.onRequestSuccessListener(response);
            } else {
                netPost(context, url, requestParams, parseUtils, cacheEntity, listener);
            }
        } else {
            netPost(context, url, requestParams, parseUtils, cacheEntity, listener);
        }
    }

    /*网络处理post请求*/
    private void netPost(Context context, String url, RequestParams requestParams, final IParseApi parseUtils, final CacheEntity cacheEntity, final RequestCompleteListener listener) {
        AsyncHttpClient asyncHttpClient = AsynHttpClientApi.newInstance().getAsyncHttpClient();
        asyncHttpClient.post(context, url, requestParams, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onRequestFailListener(statusCode, headers, responseString, throwable);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                parseUtils.jsonParser(responseString, cacheEntity, listener);
            }

            @Override
            public void onProgress(int bytesWritten, int totalSize) {
                listener.onRequestProgress(bytesWritten, totalSize);
            }
        });
    }

}
