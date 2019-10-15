package com.example.popularmovies.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName( "results" )
    public List<Model> movies;


}