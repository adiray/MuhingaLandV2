package com.example.dell.muhingalandv2;

import android.os.Handler;
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

public class MainActivity extends AppCompatActivity {


    //miscellaneous objects
    Integer responseCount;

    //declare the view objects
    SwipeRefreshLayout landSwipeRefresh; //swipe to refresh view for the land recycler view
    Boolean onRefreshing = false, infiniteLoading = false; //shows weather the user is refreshing or loading more items respectively


    //declare the recycler view objects
    RecyclerView landMainRecView;
    ArrayList<LandResponse> allLandResponseArray, filteredLandResponseArray;


    //declare the retrofit objects. All these are used with retrofit
    Retrofit.Builder builder;
    Retrofit myRetrofit;
    RetrofitClient myWebClient;
    retrofit2.Call<ArrayList<LandResponse>> allLandCall, landLocationsCall, filteredLandCall;
    Map<String, String> landFilterMap = new HashMap<String, String>(), landLocationsMap = new HashMap<String, String>(), landUserFilterMap = new HashMap<String, String>();
    Integer tableOffset = 0, filteredTableOffset = 0;   //this increases the offset from the top of the table when items are being retrieved from backendless
    String tableOffsetString = tableOffset.toString(), filteredTableOffsetString = filteredTableOffset.toString();

    //Fast adapter objects
    FastItemAdapter<LandResponse> landFastAdapter = new FastItemAdapter<>();    //create our FastAdapter which will manage everything
    FooterAdapter<ProgressItem> footerAdapter = new FooterAdapter<>();
    EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //do some house keeping
        houseKeeping();

        //set up endless the endless scroll listeners
        endlessOnScrollSetup();

        //set up the swipe to refresh feature
        swipeRefreshSetup();

        //create the query maps used in the request
        createQueryMap();

        //build the retrofit client
        buildRetrofitClient();

        //make the initial request for items
        requestLand();

    }


    /*********************************************************************************************************************************************************************************************/

    /*MY METHODS*/

    /*********************************************************************************************************************************************************************************************/


    void houseKeeping() {

        //build out main recycler view
        landMainRecView = findViewById(R.id.land_activity_rec_view);
        landMainRecView.setHasFixedSize(true);
        landMainRecView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1, 1, false));

        // fastAdapterBuild();
    }


    /*********************************************************************************************************************************************************************************************/


    void swipeRefreshSetup() {

        landSwipeRefresh = findViewById(R.id.land_swipe_refresh); //swipe to refresh view for the land recycler view

        //set the on refresh listener to the swipe to refresh view
        landSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshLand();
            }
        });

    }


    /*********************************************************************************************************************************************************************************************/


    void refreshLand() {

        tableOffset = 0;
        tableOffsetString = tableOffset.toString();
        landFilterMap.put("offset", tableOffsetString);  //update the value of the offset in the request url
        onRefreshing = true;
        infiniteLoading = false;
        requestLand();


        //stop the refreshing animation
        landSwipeRefresh.setRefreshing(false);

    }


    /*********************************************************************************************************************************************************************************************/


    void fastAdapterBuild() {

        //initialize our FastAdapter which will manage everything
        landFastAdapter = new FastItemAdapter<>();


    }

    /*********************************************************************************************************************************************************************************************/

    void endlessOnScrollSetup() {

        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore(int currentPage) {

                //important housekeeping
                footerAdapter.clear();
                footerAdapter.add(new ProgressItem().withEnabled(false));

                final Handler handler = new Handler();
                loadMoreHouses();


            }
        };

        landMainRecView.addOnScrollListener(endlessRecyclerOnScrollListener);

    }


    /*********************************************************************************************************************************************************************************************/


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
        allLandCall = myWebClient.getFilteredLand(landFilterMap);
        filteredLandCall = myWebClient.getFilteredLand(landUserFilterMap);
        landLocationsCall = myWebClient.getFilteredLand(landLocationsMap);

    }


    /*********************************************************************************************************************************************************************************************/


    void createQueryMap() {

        //fill the query map object for the retrofit query
        landFilterMap.put("pageSize", "8");
        landFilterMap.put("offset", tableOffsetString);
        landFilterMap.put("sortBy", "created%20desc");

    }


    /*********************************************************************************************************************************************************************************************/


    void requestLand() {

        allLandCall.clone().enqueue(new Callback<ArrayList<LandResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<LandResponse>> call, Response<ArrayList<LandResponse>> response) {

                if (response.isSuccessful()) {

                    if (!onRefreshing && !infiniteLoading) {

                        //perform the normal sequence of actions for a first time load
                        responseCount = response.body().size();
                        allLandResponseArray = response.body();
                        landFastAdapter.add(allLandResponseArray);
                        landMainRecView.setAdapter(footerAdapter.wrap(landFastAdapter));

                        Log.d("mylogssuccess", "success: " + response.raw().request().toString());

                    } else if (onRefreshing && !infiniteLoading) {

                        responseCount = response.body().size();
                        allLandResponseArray.clear();
                        allLandResponseArray = response.body();
                        landFastAdapter.clear();
                        landMainRecView.clearOnScrollListeners();
                        landMainRecView.addOnScrollListener(endlessRecyclerOnScrollListener);
                        landFastAdapter.add(response.body());
                        endlessRecyclerOnScrollListener.resetPageCount();

                        Log.d("mylogssuccessOR", "success: " + response.raw().request().toString());


                    } else if (!onRefreshing && infiniteLoading) {

                        responseCount = response.body().size();
                        allLandResponseArray.addAll(response.body());
                        footerAdapter.clear();
                        if (response.body().size() > 0) {
                            landFastAdapter.add(response.body());
                        } else {
                            Toast.makeText(MainActivity.this, "No more items", Toast.LENGTH_LONG).show();
                        }

                        Log.d("mylogssuccessIL", "success: " + response.raw().request().toString());

                        tableOffset = allLandResponseArray.size();
                        tableOffsetString = tableOffset.toString();
                        landFilterMap.put("offset", tableOffsetString); //update the value of the offset in the request url


                    }


                }


            }


            @Override
            public void onFailure(Call<ArrayList<LandResponse>> call, Throwable t) {

                Log.d("mylogsfailure", "error: " + t.getMessage());
                t.printStackTrace();

            }
        });

    }


    /*********************************************************************************************************************************************************************************************/


    void loadMoreHouses() {

        //TODO The allLandResponseArray exists just to give a count. Maybe the count could be more effectively stored in an integer value?

        tableOffset = allLandResponseArray.size();
        tableOffsetString = tableOffset.toString();
        landFilterMap.put("offset", tableOffsetString); //update the value of the offset in the request url
        infiniteLoading = true;
        onRefreshing = false;
        requestLand();


    }


}


/* TODO The layout does not scroll to the complete bottom. need to fix that.
 *
 * complete the endless on scroll
 * TODO wrap the adapter around the footer adapter
 *TODO Ineed to change everything
  *
   *
   *
   *
   * handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {



                    }
                }, 2000);

 *
 *
 * */

/* changed the updated and created integer to a long. might cause issues later.*/