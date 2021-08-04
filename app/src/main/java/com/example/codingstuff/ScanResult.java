package com.example.codingstuff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.UnsupportedEncodingException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScanResult extends AppCompatActivity {

    String result;
    TextView textView,Posttxt,Gettxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        textView = (TextView) findViewById(R.id.scanResult);
        Posttxt = findViewById(R.id.PosttextView);
        Gettxt = findViewById(R.id.Gettextview);

        Intent intent = getIntent();
        result = intent.getStringExtra("result");
        Toast.makeText(this, "Scan Successful", Toast.LENGTH_LONG).show();
        textView.setText(result);

        String postBody = result;
        Retrofit postretrofit = new Retrofit.Builder()
                .baseUrl("https://httpbin.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostRequestApi postRequestApi = postretrofit.create(PostRequestApi.class);
        PostModel postModel = new PostModel(result, postBody);

        Call<PostModel> postcall = postRequestApi.PostDataIntoServer(postModel);
        postcall.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                Posttxt.setText(response.body().getJson().getResult());
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {

            }
        });

        Retrofit getretrofit = new Retrofit.Builder().baseUrl("http://10.168.160.70:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        MyAPICall myAPICall = getretrofit.create(MyAPICall.class);
        Call<List> getcall = myAPICall.getData();
        getcall.enqueue(new Callback<List>() {
            @Override
            public void onResponse(Call<List> call, Response<List> response) {
                if(response.code() != 200){
                    Gettxt.setText("Check the connection");
                    return;
                }
                String jsony = "";

                Gettxt.setText(response.body().get(0).toString());
                //txt.append(response.toString());
                //jsony = "orderId= " + response.body().getOrderId();
                /*jsony = "ID= " + response.body().getId() + "\nuserId= "+ response.body().getUserId() +
                        "\ntitle= " + response.body().getTitle() + "\ncompleted= " +
                        response.body().isCompleted();
                txt.append(jsony); */
            }

            @Override
            public void onFailure(Call<List> call, Throwable t) {

            }
        });
    }
}