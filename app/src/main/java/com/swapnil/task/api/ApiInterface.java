package com.swapnil.task.api;


import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @Headers({
            "Content-Type: application/json",
    })

    @POST("user/login")
    Call<ResponseBody> login(@Body HashMap<String, String> login);


    @POST("user")
    Call<ResponseBody> signup(@Body HashMap<String, String> getUser);

    @POST("logout")
    Call<ResponseBody> logout(@Header("Authorization") String authHeader);


    @GET("user/info")
    Call<ResponseBody> getUserDetails(@Header("Authorization") String authHeader);

    @Multipart
    @POST("user/edit-cover-pic")
    Call<ResponseBody> updateEmail(@Header("Authorization") String auth,
                                                    @Part MultipartBody.Part file);

}
