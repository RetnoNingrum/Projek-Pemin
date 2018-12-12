package com.example.dretnon.android.Rest;

import com.example.dretnon.android.Model.Ruang;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    //ambil semua data ruang
    @GET("ruang")
    Call<List<Ruang>> getRuang();


    //create ruang
    @FormUrlEncoded
    @POST("ruang")
    Call<Ruang> createRuang(
            @Field("idRuang") String idRuang,
            @Field("ruang") String ruang
    );


    //delete ruang
    @DELETE("ruang/{idRuang}")
    Call<Ruang> deleteRuang(@Path("idRuang") String idRuang);


    //update ruang
    @FormUrlEncoded
    @PUT("ruang/{idRuang}")
    Call<Ruang> updateRuang(@Path("idRuang") String idRuang,
                          @Field("ruang") String ruang);

}


