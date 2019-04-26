package com.wesniemarcelin.reqresproject.service;

import com.wesniemarcelin.reqresproject.model.UserList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ReqService {
    String REQ_API_BASE_URL="https://reqres.in/api/";
    @GET ("/users")
    Observable<UserList> getUsers();
//    @POST("/users")
//    Observable<NewUser> createUser();
}
