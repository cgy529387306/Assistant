package com.android.mb.assistant.api;

import com.android.mb.assistant.retrofit.http.entity.HttpResult;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @created by cgy on 2017/6/19
 */
public interface IScheduleService {
    @POST("/ServletDispatcher")
    @FormUrlEncoded
    Observable<HttpResult<Object>> userLogin(@FieldMap Map<String, Object> requestMap);


}
