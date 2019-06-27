package com.android.mb.assistant.api;

import android.util.Log;

import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.entitys.UserBean;
import com.android.mb.assistant.retrofit.cache.transformer.CacheTransformer;
import com.android.mb.assistant.retrofit.http.RetrofitHttpClient;
import com.android.mb.assistant.utils.MACHelper;
import com.android.mb.assistant.utils.ProjectHelper;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * @Created by cgy on 2017/6/27
 */
@SuppressWarnings("unchecked")
public class ScheduleMethods extends BaseHttp {

    private ScheduleMethods(){}

    private static class SingletonHolder {
        private static final ScheduleMethods INSTANCE = new ScheduleMethods();
    }

    //获取单例
    public static ScheduleMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private IScheduleService getService() {
        return new RetrofitHttpClient.Builder()
                .baseUrl(getServerHost())
                .addDotNetDeserializer(false)
                .addLog(true)
                .build()
                .retrofit()
                .create(IScheduleService.class);
    }


    /**
     * 登录
     * @param params
     * @return
     */
    public Observable baseRequest(String requestCode,List<String> params){
        Map<String,String> requestMap = new HashMap<>();
        String requestData = MACHelper.getData(params);
        Log.e("requestData",requestData);
        Log.e("requestCode",requestCode);
        requestMap.put("code",requestCode);
        requestMap.put("data",requestData);
        requestMap.put("mac", MACHelper.workMacForApp(requestData));
        return getService().baseRequest(requestMap)
                .compose(CacheTransformer.emptyTransformer());
    }


}
