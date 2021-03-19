package com.swapnil.task.api;


import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({
            "Content-Type: application/json",
    })

    @POST("user/login")
    Call<ResponseBody> login(@Body HashMap<String, String> login);

//    @POST("user/info")
//    Call<ResponseBody> getUser(@Header("Authorization") String authHeader,
//                              @Body HashMap<String, String> getUser);

    @POST("user")
    Call<ResponseBody> signup(@Body HashMap<String, String> getUser);


    @GET("user/info")
    Call<ResponseBody> getUserDetails(@Header("Authorization") String authHeader);

}
