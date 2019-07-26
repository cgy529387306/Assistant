package com.android.mb.assistant.api;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @created by cgy on 2017/6/19
 */
public interface IScheduleService {

    @POST("/MoveHelper/ServletDispatcher")
    Observable<String> baseRequest(@QueryMap Map<String, String> requestMap);

    @POST("/MoveHelper/ServletAddCompetion")
    Observable<String> competitiveRequest(@QueryMap Map<String, String> requestMap);

    @POST("/MoveHelper/ServletAddMaterial")
    Observable<String> goodsRequest(@QueryMap Map<String, String> requestMap);

    @POST("/MoveHelper/ServletUploadImg")
    @Multipart
    Observable<String> uploadImg(@QueryMap Map<String, String> requestMap,@Part MultipartBody.Part file);

}
