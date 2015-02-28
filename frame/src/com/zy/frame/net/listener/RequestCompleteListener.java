package com.zy.frame.net.listener;

import com.zy.frame.net.response.Response;
import org.apache.http.Header;

/**
 * Created by zy on 2015/2/11.
 * 请求结束的回调
 */
public interface RequestCompleteListener {
    /**
     * 请求数据成功
     * @param response 请求成功，服务器返回经过解析的java实体
     */
    public void onRequestSuccessListener(Response response);

    /**
     * 请求数据失败
     * @param statusCode
     * @param headers
     * @param responseString
     * @param throwable
     */
    public void onRequestFailListener(int statusCode, Header[] headers, String responseString, Throwable throwable);

    /**
     * 请求数据的进度
     * @param bytesWritten
     * @param totalSize
     */
    public void onRequestProgress(int bytesWritten, int totalSize);
}
