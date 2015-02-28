package com.zy.frame.constant;


/**
 * Created by zhangyun on 15/1/2.
 */
public class Constant {

    public static final String APP_ENCODING = "UTF-8";
    public static final String REQUEST_URL = "http://121.199.38.85/houseclient/api.php";
    public static final String REGISTER_PROTOCOL_URL = "file:///android_asset/register_read.html";
    public static final String PING_TAI = "1";//平台
    public static final String UTM = "1";//渠道
    public static final int SYSTEM_TYPE_ANDROID = 1;
    public static final int MAX_SHOW_LEN = 20;


    /**
     * 分页的page size
     */
    public static final int PAGE_SIZE = 10;
    /**
     * 初始页 为 0
     */
    public static final int FIRST_PAGE = 1;


    /**
     * 保存sp的名字
     */
    public static final class sp_name {

    }

    /**
     * 保存网络返回的结果码
     */
    public static final class Result_Code {

    }

    public static final class Main_Fragment {
        public static final int FRAGMENT_HOME = 0;
        public static final int FRAGMENT_COMMUNITY = 1;
        public static final int FRAGMENT_CUSTOMER = 2;
        public static final int FRAGMENT_MYACCOUNT = 3;

    }


    public static final class Building_Detail_Fragment {
        public static final int FRAGMENT_BUILDING_DETAIL = 0;
        public static final int FRAGMENT_BUILDING_ADVANTAGE = 1;
        public static final int FRAGMENT_DISCUSSION = 2;
    }


    public static final class RequestConstantParams {
        public static final String APP_ID = "666669";
        public static final String APP_SECRET = "a5537b0f0b0438dbedf7eaa8372d4f85";
        public static final String SESSION_KEY = "u2fsdgvkX18";
        public static final String KEY = "*)8.~1`@X=^7!%#K;_$-";
    }

    public static final class RequestAct {
        public static final String SUBMIT_USER_SEND_MOBILE_CODE = "submit_user_send_mobile_code";
        public static final String SUBMIT_USER_REGISTER = "submit_user_register";
        public static final String SUBMIT_USER_LOGIN = "submit_user_login";
        public static final String SUBMIT_USER_CHANGE_PW = "submit_user_change_pw";
    }

    /**
     * 错误码
     */
    public static final class Error_Code {
        public static final int ERR_CODE_USER_LOGIN_CHECK_SESSION_INVALID = 1021;//session超时，重新登录
    }

    public static final class Load_Type {
        public static final int REFRESH = 1;
        public static final int LOADMORE = 2;
    }

    public static final class SpName {
        public static final String SP_IQUERY = "Sp_IQuery";
        public static final String SP_CITY_LIST = "SP_City_list";
        public static final String SP_IS_FIRST_USER = "SP_is_first_user";
    }

    public static final String[] housType = {"不限", "复式", "一室", "二室", "三室", "四室", "五室及以上", "别墅", "商铺"};
    /**
     * 0:所有;1:无效客
     * 户;2:未报备;3:已报备,4:
     * 已带看,5:已预约,6:已认购,
     * 7:已结佣,8:报备取消(报备
     * 后经纪人自己取消),9:报备取消
     * (销售取消),10:报备过期(报
     * 备后一直没带看,系统取消), 11:
     * 报备过期(带看后一直没成交,
     * 系统取消),12:已评价
     */

    public static final String[] ORDER_STATUS = {"所有", "无效客户", "未报备", "已报备", "已带看", "已预约",
            "已认购", "已结佣", "报备已取消", "报备被取消", "报备过期", "未成交取消", "已评价"};

    /**
     * 推送类别根据type字段区分，1：系统，2：身份审核，3：订单状态的变化，4：论坛；
     */
    public static final class PushType {
        public static final int SYSTEM_BROADCAST = 1;//系统
        public static final int IDENTITY_VERIFICATION = 2;//身份审核
        public static final int ORDER_STATE_CHANGE = 3;//订单状态的变化
        public static final int COMMUNITY = 4;//论坛

    }
}
