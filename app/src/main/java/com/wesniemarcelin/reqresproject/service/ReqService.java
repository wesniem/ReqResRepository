package com.wesniemarcelin.reqresproject.service;

import com.wesniemarcelin.reqresproject.model.NewUser;
import com.wesniemarcelin.reqresproject.model.UserList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ReqService {
    String REQ_API_BASE_URL = "https://reqres.in/api/";

    @GET("users")
    Observable<UserList> getUsers();

    @POST("users")
    @FormUrlEncoded
    Observable<NewUser> createUser(@Field("name") String name, @Field("job") String job);
}
