package com.example.dell.muhingalandv2;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RetrofitClient {

    @GET("land")
    Call<ArrayList<LandResponse>> getFilteredLand(@QueryMap(encoded = true) Map<String, String> userFilters_Land);


    @GET("artist")
    Call<ArrayList<ArtistResponse>> getFilteredArtist(@QueryMap(encoded = true) Map<String, String> userFilters_Artist);



}


//https://api.backendless.com/125AF8BD-1879-764A-FF22-13FB1C162400/6F40C4D4-6CFB-E66A-FFC7-D71E4A8BF100/data/artist?where=name%3D%20'Kygo'&loadRelations=album