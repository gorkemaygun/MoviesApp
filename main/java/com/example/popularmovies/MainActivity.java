package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.popularmovies.Model.Model;
import com.example.popularmovies.Model.MovieResponse;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;


import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewDisplay;
    private Model model;
    private MovieAdapter mMovieAdapter;
    private List<Model> items=new ArrayList<>();
    private Api api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mRecyclerViewDisplay=findViewById( R.id.rv_layoutdisplay );
        initAdapter();
        OkHttpClient.Builder client=new OkHttpClient.Builder();
        client.addInterceptor( new LoggingInterceptor.Builder()
                .loggable( BuildConfig.DEBUG )
                .setLevel( Level.BASIC )
                .log( Platform.INFO )
                .request( "Request" )
                .response( "Response" )
                .addQueryParam( "api_key", "b644ab6b14fc5346cabffe34357d92a0" )
                .executor( Executors.newSingleThreadExecutor() )
                .build() );
        OkHttpClient okHttpClient=client.build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl( Api.BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .client( okHttpClient )
                .build();
        api=retrofit.create( Api.class );
        getMostPopular();
    }

    private void initAdapter() {
        mMovieAdapter=new MovieAdapter( items, new OnItemClickListener() {
            @Override
            public void itemClick(Model model) {
                launchDetailActivity( model );
            }
        } );
        mRecyclerViewDisplay.setLayoutManager( new GridLayoutManager( getApplicationContext(), 2 ) );
        mRecyclerViewDisplay.setAdapter( mMovieAdapter );
    }

    private void getMostPopular(){
        api.getPopularMovies().enqueue( new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i( "onSuccess", response.body().toString() );
                        items.clear();
                        items.addAll( response.body().movies );
                        mMovieAdapter.notifyDataSetChanged();
                    } else {
                        Log.i( "onEmptyResponse", "Returned empty response" );
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText( getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    private void getTopRatedMovies(){
        api.getTopRatedMovies().enqueue( new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i( "onSuccess", response.body().toString() );
                        items.clear();
                        items.addAll( response.body().movies );
                        mMovieAdapter.notifyDataSetChanged();
                    } else {
                        Log.i( "onEmptyResponse", "Returned empty response" );
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText( getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    private void launchDetailActivity(Model position) {
        Intent intent=new Intent( this, DetailActivity.class );
        intent.putExtra( "MODEL", position );
        startActivity( intent );
    }

    interface OnItemClickListener {
        void itemClick(Model model);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate( R.menu.main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.action_top_rated){
            getTopRatedMovies();
        } else {
            getMostPopular();
        }
        return true;
    }
}