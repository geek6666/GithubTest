package com.zy.frame.net.parse.impl;

import com.google.gson.Gson;
import com.zy.frame.entity.OptEntity;
import com.zy.frame.net.parse.base.BaseParseApi;

/**
 * Created by Administrator on 2015/2/11.
 */
public class SubmitUserSendMobileCode extends BaseParseApi {

    @Override
    public OptEntity parse(String jsonContent) {
        Gson gson = new Gson();
        OptEntity baseEntity = gson.fromJson(jsonContent, OptEntity.class);
        return baseEntity;
    }
}
