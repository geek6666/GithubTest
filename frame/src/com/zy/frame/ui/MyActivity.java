package com.zy.frame.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.zy.frame.R;
import com.zy.frame.utils.AnimatorUtils;

public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private TextView mTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mTextView = (TextView) findViewById(R.id.textview);
        Button button = (Button) findViewById(R.id.button);

        mTextView.setOnClickListener(this);
        button.setOnClickListener(this);
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


//        RequestApi requestApi = new RequestApi();
//        RequestParams params = ParamsApi.newInstance().get_city_list();
//        CityParse cityParse = new CityParse();
//        requestApi.post(this, params, cityParse, new BaseRequestCompleteListener() {
//            @Override
//            public void onRequestSuccessListener(Response response) {
//                List<City> cityList = (List<City>) response.content;
//                System.out.println(cityList);
//            }
//
//            @Override
//            public void onRequestFailListener(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//
//            }
//        });

        AnimatorUtils.newInstance().shakeAnimator(mTextView,500);
    }
}
