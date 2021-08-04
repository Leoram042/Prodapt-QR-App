package com.example.codingstuff;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPICall {

    // http://run.mocky.io/        v3/90fea295-8b4d-4e7c-be1e-f0788188102f

    @GET("afService_sow2_last_release/order/getInfoByOrderId?orderId=1")
    Call<List> getData();

}
