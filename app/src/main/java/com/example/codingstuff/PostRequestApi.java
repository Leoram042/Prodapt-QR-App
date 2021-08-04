package com.example.codingstuff;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostRequestApi {

    @POST("post")
    Call<PostModel> PostDataIntoServer(@Body PostModel postModel);

}