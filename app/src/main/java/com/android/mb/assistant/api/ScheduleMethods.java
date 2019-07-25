package com.android.mb.assistant.api;

import com.android.mb.assistant.retrofit.cache.transformer.CacheTransformer;
import com.android.mb.assistant.retrofit.http.RetrofitHttpClient;
import com.android.mb.assistant.utils.MACHelper;

import java.util.Map;

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



    public Observable baseRequest(String requestCode,Map<String,String> requestMap){
        requestMap.put("code",requestCode);
        requestMap.put("mac", MACHelper.workMacForApp(requestCode));
        return getService().baseRequest(requestMap)
                .compose(CacheTransformer.emptyTransformer());
    }


    public Observable competitiveRequest(String requestCode,Map<String,String> requestMap){
        requestMap.put("code",requestCode);
        requestMap.put("mac", MACHelper.workMacForApp(requestCode));
        return getService().competitiveRequest(requestMap)
                .compose(CacheTransformer.emptyTransformer());
    }


    public Observable goodsRequest(String requestCode,Map<String,String> requestMap){
        requestMap.put("code",requestCode);
        requestMap.put("mac", MACHelper.workMacForApp(requestCode));
        return getService().goodsRequest(requestMap)
                .compose(CacheTransformer.emptyTransformer());
    }


}
