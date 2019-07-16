package com.android.mb.assistant.api;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @created by cgy on 2017/6/19
 */
public interface IScheduleService {

    @POST("/MoveHelper/ServletDispatcher")
    Observable<String> baseRequest(@QueryMap Map<String, String> requestMap);


}
