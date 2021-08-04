package com.example.codingstuff;

import com.google.gson.annotations.SerializedName;

public class PostModel {
    String result;

    json json;

    public PostModel(String result, String bodyPost){
        this.result = result;
    }

    public String getResult(){
        return result;
    }

    public json getJson(){
        return json;
    }

}