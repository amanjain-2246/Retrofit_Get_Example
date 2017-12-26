package com.example.mobile_v_10.myretrofitapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MOBILE-V-10 on 8/19/2017.
 */

public class RestClient {
    public static final String Baseurl="https://dog.ceo/";
    private static Retrofit retrofit=null;

    public static Retrofit getClient() {
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(Baseurl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
