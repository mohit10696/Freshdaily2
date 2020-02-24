package com.example.freshdaily.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofit {

    public static apinterface getapi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apinterface.JSONURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apinterface api = retrofit.create(apinterface.class);
        return  api;
    }

}
