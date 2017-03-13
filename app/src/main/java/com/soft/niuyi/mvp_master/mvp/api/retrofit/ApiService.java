package com.soft.niuyi.mvp_master.mvp.api.retrofit;

import com.soft.niuyi.mvp_master.entity.UserInfoEntity;
import com.soft.niuyi.mvp_master.mvp.api.rxandroid.HttpResultEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    @GET("index")
    Observable<HttpResultEntity<UserInfoEntity>> login(@Query("type") String type, @Query("key") String key);

}
