package com.zy.frame.net.request;

import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by Administrator on 2015/2/12.
 */
public class AsynHttpClientApi {
    private static AsynHttpClientApi asynHttpClientApi;
    private AsyncHttpClient mAsyncHttpClient;

    private AsynHttpClientApi() {
        this.mAsyncHttpClient = new AsyncHttpClient();
    }

    public static AsynHttpClientApi newInstance() {
        if (asynHttpClientApi == null) {
            synchronized (AsynHttpClientApi.class) {
                if (asynHttpClientApi == null) {
                    asynHttpClientApi = new AsynHttpClientApi();
                }
            }
        }
        return asynHttpClientApi;
    }

    /**
     * 获取单例的AsynHttpClient
     * @return
     */
    public AsyncHttpClient getAsyncHttpClient() {
        return mAsyncHttpClient;
    }

}
