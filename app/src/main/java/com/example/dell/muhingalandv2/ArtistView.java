package com.example.dell.muhingalandv2;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.mikepenz.fastadapter.adapters.FooterAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter_extensions.items.ProgressItem;
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistView extends AppCompatActivity {


    //miscellaneous objects
    Boolean onRefreshing = false, infiniteLoading = false;
    String selectedArtistName , selectedArtistNameQueryString;


    //declare the view objects
    SwipeRefreshLayout artistViewAlbumRecViewSwipeRefresh;


    //declare the recycler view objects
    RecyclerView artistViewAlbumRecView;
    ArrayList<ArtistViewAlbumResponse> allAlbumResponseArray;

    //declare the retrofit objects. All these are used with retrofit
    Retrofit.Builder builder;
    Retrofit myRetrofit;
    RetrofitClient myWebClient;
    retrofit2.Call<ArrayList<ArtistViewAlbumResponse>> artistViewAllAlbumsCall;
    Map<String, String> albumsFilterMap = new HashMap<String, String>();
    Integer tableOffset = 0;   //this increases the offset from the top of the table when items are being retrieved from backendless
    String tableOffsetString = tableOffset.toString();


    //Fast adapter objects
    FastItemAdapter<ArtistViewAlbumResponse> artistViewAlbumFastAdapter = new FastItemAdapter<>();    //create our FastAdapter which will manage everything
    FooterAdapter<ProgressItem> footerAdapter = new FooterAdapter<>();
    EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_view);


        //retrieve the data from the intent extras

        //get the intent that started this activity
        Intent intent = getIntent();
        selectedArtistName = intent.getStringExtra(MusicHome.EXTRA_ARTIST_NAME);
        selectedArtistNameQueryString = "name%3D%20"+"'"+selectedArtistName+"'";
                //name%3D%20'Kygo'


        //Initialize the views
        artistViewAlbumRecViewSwipeRefresh = findViewById(R.id.artist_view_album_swipe_refresh);


        //build out the main recycler view
        artistViewAlbumRecView = findViewById(R.id.artist_view_album_rec_view);
        artistViewAlbumRecView.setHasFixedSize(true);
        artistViewAlbumRecView.setLayoutManager(new GridLayoutManager(ArtistView.this, 2, 1, false));


        //initialize our FastAdapter which will manage everything
        artistViewAlbumFastAdapter = new FastItemAdapter<>();


        //initialize the endless scroll listener
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(footerAdapter) {
            @Override
            public void onLoadMore(int currentPage) {


                footerAdapter.clear();
                footerAdapter.add(new ProgressItem().withEnabled(false));

                //Todo method to load more albums
                loadMoreAlbums();

            }

        };


        //set the on refresh listener to the swipe to refresh view
        artistViewAlbumRecViewSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                //Todo method to refresh the albums
                refreshAlbums();


            }


        });


        //set the infinite/endless load on scroll listener to the recycler view
        artistViewAlbumRecView.addOnScrollListener(endlessRecyclerOnScrollListener);


        //fill the query map object for the retrofit query
        albumsFilterMap.put("where",selectedArtistNameQueryString);
        albumsFilterMap.put("pageSize", "4");
        albumsFilterMap.put("offset", tableOffsetString);
        albumsFilterMap.put("sortBy", "created%20desc");
        albumsFilterMap.put("loadRelations", "album");


        buildRetrofitClient();  //build the retrofit client

        requestAlbums(); //make the initial / first  houses request


    }


    /*************************************************************************************************************************************************/


    void buildRetrofitClient() {

        //initialize the retrofit client builder using the backendless.com api
        builder = new Retrofit.Builder();
        builder.baseUrl("http://api.backendless.com/125AF8BD-1879-764A-FF22-13FB1C162400/6F40C4D4-6CFB-E66A-FFC7-D71E4A8BF100/data/")
                .addConverterFactory(GsonConverterFactory.create());

        //use your builder to build a retrofit object
        myRetrofit = builder.build();

        //create a retrofit client using the retrofit object
        myWebClient = myRetrofit.create(RetrofitClient.class);

        //create your call using the retrofit client
        artistViewAllAlbumsCall = myWebClient.getFilteredArtistWithAlbum(albumsFilterMap);

    }

    /*************************************************************************************************************************************************/


    void requestAlbums() {

        //make the call
        artistViewAllAlbumsCall.clone().enqueue(new Callback<ArrayList<ArtistViewAlbumResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<ArtistViewAlbumResponse>> call, Response<ArrayList<ArtistViewAlbumResponse>> response) {

                if (!onRefreshing && !infiniteLoading) {

                    //perform the normal sequence of actions for a first time load
                    allAlbumResponseArray = response.body();
                    artistViewAlbumFastAdapter.add(allAlbumResponseArray);
                    artistViewAlbumRecView.setAdapter(footerAdapter.wrap(artistViewAlbumFastAdapter));


                    Log.d("myLogsRequestUrl", response.raw().request().url().toString());

                } else if (onRefreshing && !infiniteLoading) {

                    //perform the sequence of actions for a refreshed load
                    allAlbumResponseArray.clear();
                    allAlbumResponseArray = response.body();
                    artistViewAlbumFastAdapter.clear();
                    artistViewAlbumRecView.clearOnScrollListeners();
                    artistViewAlbumRecView.addOnScrollListener(endlessRecyclerOnScrollListener);
                    artistViewAlbumFastAdapter.add(response.body());
                    endlessRecyclerOnScrollListener.resetPageCount();


                    Log.d("myLogsRequestUrlOR", response.raw().request().url().toString());


                } else if (infiniteLoading && !onRefreshing) {

                    allAlbumResponseArray.addAll(response.body());
                    footerAdapter.clear();
                    if (response.body().size() > 0) {
                        artistViewAlbumFastAdapter.add(response.body());
                    } else {
                        Toast.makeText(ArtistView.this, "No more items", Toast.LENGTH_LONG).show();
                    }


                    Log.d("myLogsRequestUrlIL", response.raw().request().url().toString() + " table offset = " + tableOffset);
                    infiniteLoading = false;


                }

                Log.d("myLogsOnSuccess", "onResponse: response successful");


            }

            @Override
            public void onFailure(Call<ArrayList<ArtistViewAlbumResponse>> call, Throwable t) {

                Log.d("myLogsOnFailure", "onResponse: response unsuccessful");

            }
        });

    }

    /*************************************************************************************************************************************************/


    void refreshAlbums() {    //method called when user attempts to refresh the albums recycler view


        tableOffset = 0;
        tableOffsetString = tableOffset.toString();
        albumsFilterMap.put("offset", tableOffsetString);  //update the value of the offset in the request url
        onRefreshing = true;
        infiniteLoading = false;
        requestAlbums();

        //stop the refreshing animation
        artistViewAlbumRecViewSwipeRefresh.setRefreshing(false);


    }


    /*************************************************************************************************************************************************/


    void loadMoreAlbums() {

        //TODO The allHousesResponseArray exists just to give a count. Maybe the count could be more effectively stored in an integer value?
        tableOffset = allAlbumResponseArray.size();
        tableOffsetString = tableOffset.toString();
        albumsFilterMap.put("offset", tableOffsetString);    //update the value of the offset in the request url
        Log.d("myLogs", "loadMoreAlbums: " + albumsFilterMap.toString());
        infiniteLoading = true;
        onRefreshing = false;
        requestAlbums();
    }


    /*************************************************************************************************************************************************/


}