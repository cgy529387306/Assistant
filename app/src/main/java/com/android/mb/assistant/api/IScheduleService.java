package com.android.mb.assistant.api;

import com.android.mb.assistant.entitys.UserBean;
import com.android.mb.assistant.retrofit.http.entity.HttpResult;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @created by cgy on 2017/6/19
 */
public interface IScheduleService {

    @POST("/ServletDispatcher")
    Observable<HttpResult<UserBean>> userLogin(@QueryMap Map<String, Object> requestMap);


}
