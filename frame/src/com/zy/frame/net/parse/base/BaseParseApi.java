package com.zy.frame.net.parse.base;


import com.zy.frame.net.cache.CacheEntity;
import com.zy.frame.net.listener.RequestCompleteListener;
import com.zy.frame.net.response.Response;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/2/11.
 * 请求参数的解析
 */
public abstract class BaseParseApi implements IParseApi {

    @Override
    public void jsonParser(String json, CacheEntity cacheEntity, RequestCompleteListener listener) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            Response response = new Response();
            response.error = jsonObject.getInt("error");
            response.message = jsonObject.getString("message");
            String content = jsonObject.getString("content");
            Object obj = parse(content);
            response.content = obj;
            listener.onRequestSuccessListener(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析content内容
     * @param jsonContent
     * @return
     */
    public abstract Object parse(String jsonContent);
}
