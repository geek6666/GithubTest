package com.zy.frame.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.loopj.android.http.RequestParams;
import com.zy.frame.R;
import com.zy.frame.entity.City;
import com.zy.frame.net.listener.BaseRequestCompleteListener;
import com.zy.frame.net.parse.impl.CityParse;
import com.zy.frame.net.request.ParamsApi;
import com.zy.frame.net.request.RequestApi;
import com.zy.frame.net.response.Response;
import org.apache.http.Header;

import java.util.List;

public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView mTextView = (TextView) findViewById(R.id.textview);

        mTextView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
//        RequestParams params = ParamsApi.newInstance().submit_user_send_mobile_code("18611290987", "1");
//        SubmitUserSendMobileCode parseUtils = new SubmitUserSendMobileCode();
//        RequestApi<OptEntity> requestApi = new RequestApi<>();
//        requestApi.post(this, params, parseUtils, new BaseRequestCompleteListener<OptEntity>() {
//            @Override
//            public void onRequestSuccessListener(Response<OptEntity> response) {
//                System.out.println(response);
//            }
//
//            @Override
//            public void onRequestFailListener(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//
//            }
//        });


        RequestApi requestApi = new RequestApi();
        RequestParams params = ParamsApi.newInstance().get_city_list();
        CityParse cityParse = new CityParse();
        requestApi.post(this, params, cityParse, new BaseRequestCompleteListener() {
            @Override
            public void onRequestSuccessListener(Response response) {
                List<City> cityList = (List<City>) response.content;
                System.out.println(cityList);
            }

            @Override
            public void onRequestFailListener(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }
        });
    }
}
