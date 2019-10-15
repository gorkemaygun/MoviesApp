package com.example.popularmovies;

import com.example.popularmovies.Model.Model;
import com.example.popularmovies.Model.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL="https://api.themoviedb.org/3/";
    String IMAGE_URL="https://image.tmdb.org/t/p/w500/";

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies();
    @GET ("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies();
}
