package com.example.dretnon.android.Rest;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://192.168.43.121:8080/ruang";
//    public static final String BASE_URL = "http://localhost/api/public/";
    private static ApiClient mInstance = null;
    private Retrofit retrofit;


    public ApiClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiClient getInstance(){
        if(mInstance == null){
            mInstance = new ApiClient();
        }
        return mInstance;
    }

    public ApiInterface getApi(){
        return retrofit.create(ApiInterface.class);
    }

}
