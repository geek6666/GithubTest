package com.zy.frame.net.request;

import android.text.TextUtils;
import com.loopj.android.http.RequestParams;
import com.zy.frame.constant.Constant;
import com.zy.frame.utils.Md5Util;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/2/11.
 * 请求参数工具
 */
public class ParamsApi {

    private static ParamsApi mParamsApi;

    private ParamsApi() {
    }

    public static ParamsApi newInstance() {
        if (mParamsApi == null) {
            synchronized (ParamsApi.class) {
                if (mParamsApi == null) {
                    mParamsApi = new ParamsApi();
                }
            }
        }
        return mParamsApi;
    }

    /**
     * 参数加密
     */
    private RequestParams encrypt(Map<String, String> mParams) {
        RequestParams mRequestParams = new RequestParams();
        String act = mParams.remove("act");
        String data = new JSONObject(mParams).toString();
        data = TextUtils.isEmpty(data) ? "{}" : data;
        String timestamp = String.valueOf(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        sb.append(act).append(Constant.RequestConstantParams.APP_ID).append(Constant.RequestConstantParams.APP_SECRET)
                .append(Constant.RequestConstantParams.SESSION_KEY).append(timestamp)
                .append(data).append(Constant.RequestConstantParams.KEY);
        String sig = Md5Util.MD5(sb.toString());
        mRequestParams.put("act", act);
        mRequestParams.put("app_id", Constant.RequestConstantParams.APP_ID);
        mRequestParams.put("app_secret", Constant.RequestConstantParams.APP_SECRET);
        mRequestParams.put("session_key", Constant.RequestConstantParams.SESSION_KEY);
        mRequestParams.put("timestamp", timestamp);
        mRequestParams.put("sig", sig);
        mRequestParams.put("data", data);
        return mRequestParams;
    }

    public RequestParams submit_user_send_mobile_code(String mobile, String from_func) {
        Map<String, String> params = new HashMap<>();
        params.put("act", "submit_user_send_mobile_code");
        params.put("mobile", mobile);
        params.put("from_func", from_func);
        return encrypt(params);
    }

    public RequestParams get_city_list(){
        Map<String, String> params = new HashMap<>();
        params.put("act", "get_city_list");
        return encrypt(params);
    }
}
