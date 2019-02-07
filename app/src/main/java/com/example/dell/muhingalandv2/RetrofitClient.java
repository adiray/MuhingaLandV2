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


    @GET("artist")
    Call<ArrayList<ArtistViewAlbumResponse>> getFilteredArtistWithAlbum(@QueryMap(encoded = true) Map<String, String> userFilters_Artist_withAlbum);


    @GET("album")
    Call<ArrayList<SongResponse>> getAlbumWithSongs(@QueryMap(encoded = true) Map<String, String> userFilters_Album_with_Songs);


}


   /* @Nullable
    @Override
    public View onBind(@NonNull RecyclerView.ViewHolder viewHolder) {

        //return super.onBind(viewHolder);

        if (viewHolder instanceof SongResponse.SongViewHolder) {
            return ((SongResponse.SongViewHolder) viewHolder).play_button;
        }
        return null;


    }*/


//https://api.backendless.com/125AF8BD-1879-764A-FF22-13FB1C162400/6F40C4D4-6CFB-E66A-FFC7-D71E4A8BF100/data/artist?where=name%3D%20'Kygo'&loadRelations=album
//https://api.backendless.com/125AF8BD-1879-764A-FF22-13FB1C162400/6F40C4D4-6CFB-E66A-FFC7-D71E4A8BF100/data/album?where=name%3D'views'&loadRelations=songs