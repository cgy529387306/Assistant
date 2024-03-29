package com.android.mb.assistant.api;


import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.retrofit.http.entity.HttpResult;
import com.android.mb.assistant.utils.ToastHelper;

import java.util.HashMap;
import java.util.Map;

import rx.functions.Func1;

/**
 * @Description 基础
 * @created by cgy on 2017/6/19
 * @version v1.0
 *
 */

public class BaseHttp {

//    //测试地址
    public static final String BASE_URL = "http://114.115.136.72:8080";
    //正式地址
//    public static final String BASE_URL = "http://112.30.210.148:8888";

    public String getServerHost() {
        return BASE_URL;
    }

    @SuppressWarnings("unchecked")
    static class HttpCacheResultFunc<T> implements Func1 {
        @Override
        public Object call(Object o) {
            HttpResult<T> httpResult;
            if (o instanceof HttpResult) {
                httpResult = (HttpResult<T>) o;
                if ("00".equals(httpResult.getCode())){
                    return httpResult.getData();
                }else if ("01".equals(httpResult.getCode())){
                    ToastHelper.showToast(httpResult.getMessage());
                }else {
                    ToastHelper.showToast("请求失败");
                }
            }
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    static class HttpResultFunc<T> implements Func1 {
        @Override
        public Object call(Object o) {
            HttpResult<T> httpResult;
            if (o instanceof HttpResult) {
                httpResult = (HttpResult<T>) o;
                return httpResult;
            }
            return null;
        }
    }

    /**
     * 获取头部信息
     *
     * @return Map
     */
    Map<String, String> getHead() {
        Map<String, String> cloudOfficeHeader = new HashMap<String, String>();
        if (CurrentUser.getInstance().isLogin()){

        }
        return cloudOfficeHeader;
    }

}
